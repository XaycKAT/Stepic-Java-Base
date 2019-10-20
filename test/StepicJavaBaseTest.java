import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class StepicJavaBaseTest {

    @Test
    public void isPalindrome() {
    }

    @Test
    public void factorial() {
    }

    @Test
    public void mergeArrays() {
    }

    @Test
    public void printTextPerRole() {
        String[] roles = {"Городничий", "Аммос Федорович", "Артемий Филиппович", "Лука Лукич"};
        String[] text = {"Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
                "Аммос Федорович: Как ревизор?",
                "Артемий Филиппович: Как ревизор?",
                "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
                "Аммос Федорович: Вот те на!",
                "Артемий Филиппович: Вот не было заботы, так подай!",
                "Лука Лукич: Господи боже! еще и с секретным предписаньем!"};
        String expected = "Городничий:\n" +
                "1) Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.\n" +
                "4) Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.\n" +
                "\n" +
                "Аммос Федорович:\n" +
                "2) Как ревизор?\n" +
                "5) Вот те на!\n" +
                "\n" +
                "Артемий Филиппович:\n" +
                "3) Как ревизор?\n" +
                "6) Вот не было заботы, так подай!\n" +
                "\n" +
                "Лука Лукич:\n" +
                "7) Господи боже! еще и с секретным предписаньем!";
        String actual = StepicJavaBase.printTextPerRole(roles,text);
        Assert.assertEquals(expected,actual);

        String[] roles1 = {"Городничий", "Аммос Федорович", "Артемий Филиппович", "Лука Лукич", "Антон"};
        String[] text1 = {"Городничий: Аммос Федорович, тут другая роль!!! Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
                "Аммос Федорович: Как ревизор?",
                "Артемий Филиппович: Как ревизор?",
                "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
                "Аммос Федорович: Вот те на!",
                "Артемий Филиппович: Вот не было заботы, так подай!",
                "Лука Лукич: Господи боже! еще и с секретным предписаньем!"};
        String expected1 = "Городничий:\n" +
                "1) Аммос Федорович, тут другая роль!!! Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.\n" +
                "4) Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.\n" +
                "\n" +
                "Аммос Федорович:\n" +
                "2) Как ревизор?\n" +
                "5) Вот те на!\n" +
                "\n" +
                "Артемий Филиппович:\n" +
                "3) Как ревизор?\n" +
                "6) Вот не было заботы, так подай!\n" +
                "\n" +
                "Лука Лукич:\n" +
                "7) Господи боже! еще и с секретным предписаньем!\n" +
                "\n" +
                "Антон:";
        String actual1 = StepicJavaBase.printTextPerRole(roles1,text1);
        Assert.assertEquals(expected1,actual1);
    }

//    @Test
//    public void moveRobot() {
//        StepicJavaBase.Robot robot = new StepicJavaBase.Robot(0,0, StepicJavaBase.Direction.UP);
//        StepicJavaBase.moveRobot(robot,0,0);
//        assertEquals("0 0",robot.getPosition());
//
//        robot.setPosition(1,1,"RIGHT");
//        StepicJavaBase.moveRobot(robot,3,0);
//        assertEquals("3 0",robot.getPosition());
//
//        robot.setPosition(-6,-3,"UP");
//        StepicJavaBase.moveRobot(robot,-3,0);
//        assertEquals("-3 0",robot.getPosition());
//
//        robot.setPosition(9,7,"UP");
//        StepicJavaBase.moveRobot(robot,3,-5);
//        assertEquals("3 -5",robot.getPosition());
//
//        robot.setPosition(10,-10,"LEFT");
//        StepicJavaBase.moveRobot(robot,-3,-5);
//        assertEquals("-3 -5",robot.getPosition());
//
//        robot.setPosition(1,1,"RIGHT");
//        StepicJavaBase.moveRobot(robot,1,1);
//        assertEquals("1 1",robot.getPosition());
//
//        robot.setPosition(1,1,"RIGHT");
//        StepicJavaBase.moveRobot(robot,1,7);
//        assertEquals("1 7",robot.getPosition());
//
//        robot.setPosition(-5,7,"RIGHT");
//        StepicJavaBase.moveRobot(robot,1,7);
//        assertEquals("1 7",robot.getPosition());
//    }
//

    @Test
    public void integrate() {

    }
}