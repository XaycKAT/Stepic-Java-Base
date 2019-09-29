import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class Except {

    public static String getCallerClassAndMethodName() {
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        String message = "";
        if(stackTraceElements.length<3)
            return null;
        if (stackTraceElements.length >= 3) {
            StackTraceElement element = stackTraceElements[2];
            String className = element.getClassName();
            String methodName = element.getMethodName();
            message = className + "#" + methodName;
        }
        return message;
    }

    private static void configureLogging() {
        Logger logger = Logger.getLogger("org.stepic.java");
        logger.setLevel(Level.ALL);
        XMLFormatter f = new XMLFormatter();
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        ch.setFormatter(f);
        logger.addHandler(ch);
        logger.setUseParentHandlers(false);

        Logger loggerA = Logger.getLogger("org.stepic.java.logging.ClassA");
        loggerA.setLevel(Level.ALL);

        Logger loggerB = Logger.getLogger("org.stepic.java.logging.ClassB");
        loggerB.setLevel(Level.WARNING);

    }
    //////////////////////////////////////////////////////////////////

    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";
    /*
Интерфейс: сущность, которую можно отправить по почте.
У такой сущности можно получить от кого и кому направляется письмо.
*/
    public static interface Sendable {
        String getFrom();
        String getTo();
    }

    /*
Абстрактный класс,который позволяет абстрагировать логику хранения
источника и получателя письма в соответствующих полях класса.
*/
    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;

            return true;
        }

    }
    /*
Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
*/
    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            if (message != null ? !message.equals(that.message) : that.message != null) return false;

            return true;
        }

    }
    /*
Посылка, содержимое которой можно получить с помощью метода `getContent`
*/
    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            if (!content.equals(that.content)) return false;

            return true;
        }

    }

    /*
Класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность.
*/
    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            if (!content.equals(aPackage.content)) return false;

            return true;
        }
    }

    /*
Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
*/
    public static interface MailService {
        Sendable processMail(Sendable mail);
    }

    /*
    Класс, в котором скрыта логика настоящей почты
    */
    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }

    public static class UntrustworthyMailWorker implements MailService {
        RealMailService realMailService = new RealMailService();
        MailService[] mailServices ;

        public UntrustworthyMailWorker(MailService[] mailServices) {
            this.mailServices = mailServices;
        }

        public RealMailService getRealMailService(){
            return realMailService;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            for(MailService mailService : mailServices){
                mail = mailService.processMail(mail);
            }
            return getRealMailService().processMail(mail);
        }
    }

    public static class Spy implements MailService {
        private Logger logSpy ;
        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                if (mail.getFrom().equals(AUSTIN_POWERS) || mail.getTo().equals(AUSTIN_POWERS)) {
                    logSpy.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                            new Object[]{mail.getFrom(), mail.getTo(),((MailMessage) mail).getMessage()});
                } else {
                    logSpy.log(Level.INFO, "Usual correspondence: from {0} to {1}",
                            new Object[]{mail.getFrom(),mail.getTo()});
                }
            }
            return mail;
        }

        public Spy(Logger logSpy) {
            this.logSpy = logSpy;
        }
    }

    public static class Thief implements MailService {

        private int minPrice;
        private static int stolenValue = 0;

        public Thief(int minPrice) {
            this.minPrice = minPrice;
        }

        public int getStolenValue(){
            return stolenValue;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            MailPackage pack;
            if (mail instanceof MailPackage) {
                pack = (MailPackage) mail;
                if (pack.getContent().getPrice() >= minPrice) {
                    stolenValue += pack.getContent().getPrice();
                    return new MailPackage(pack.getFrom(), pack.getTo(), new Package("stones instead of " + pack.getContent().getContent(), 0));
                }
            }
            return mail;
        }
    }

    public class Inspector implements MailService {
        @Override
        public Sendable processMail(Sendable mail) {
            MailPackage mailPackage;
            if (mail instanceof MailPackage) {
                mailPackage = (MailPackage) mail;
                if (mailPackage.getContent().getContent().equals(WEAPONS)
                        || mailPackage.getContent().getContent().equals(BANNED_SUBSTANCE)) {
                    throw new IllegalPackageException();
                }
                if (mailPackage.getContent().getContent().contains("stones")) {
                    throw new StolenPackageException();
                }
            }
            return mail;
        }
    }
    public static class IllegalPackageException extends RuntimeException {
        public IllegalPackageException() {
        }
    }

    public static class StolenPackageException extends RuntimeException {
        public StolenPackageException() {
        }

    }

}
