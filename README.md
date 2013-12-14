GLO4003
=======

Website of the Rouge et Or sports teams of Laval University (Québec City).
This project is part of GLO-4003 class.

### How to start the application

#### Installing the project

The project is developped as a Spring Project. However, we use some Aspects, which means you have to convert the project to an AspectJ project.

To do so, right click on your project in Eclipse explorer, then "configure" and "convert to AspectJ project.

Also it use the JavaMail API, it is necessary to link the javax.mail.jar to the classpath. To do so locate the JAR file in ./project/Java Resources/Librairies/javax.mail.jar (or download it from http://www.oracle.com/technetwork/java/javamail/javamail-138606.html and include in project), then right clic on it -> Build Path -> Add to Build Path.

#### Running the application

You need to run the project as a Java Application. We have a main method located in ./project/src/main/java/com/glo4003/project/global/BootLoader.java which is in charge of starting the web server and everything for you.

#### Using fake informations

When you start the application, the files situated into the folder

    src/main/java/database/files

would be used as data pool. The files already present can be used as fake data to test the application. To use them, copy all the .template files inside the same folder and change the .template by .xml

You can edit these files how you want but you should keep the same structure and tag name, elsewise the application would not be able to read the informations. As you can see these file are easily readable and editable by everyone. Each class serialized is saved inside a xml file containing all the data of classes with the same type.

Note that tests would usually delete the .xml files.

### How to serialize your models

1. First,  the model that you want to serialize must implement the ModelInterface class

        public class UserModel implements ModelInterface

    If your model only contains objects like String, long and all other basic java
    variables than your class is ready to be serialized and you can go to step 3, otherwise, go to step 2.

2.  To serialize a model inside another model, you need to add annotations that will
    indicate it is not a basic java variable.

        @XStreamConverter(XmlObjectConverter.class)

    This is an example of how it works.

        private static class TestClass implements ModelInterface {
            long id = 1;
            String test1 = "test1";
            String test2 = "test2";

            @XStreamConverter(XmlObjectConverter.class)
            TestClass2 testClass2 = new TestClass2();

            public Long getId() {
                return id;
            }
        }

    If you want to serialize a list of models, instead use that annotation.

        @XStreamConverter(XmlArrayListConverter.class)


    This is an example of how it works.

        private static class TestClass3 implements ModelInterface {
            long id = 1;
            String test1 = "test1";
            String test2 = "test2";

            @XStreamConverter(XmlArrayListConverter.class)
            List<TestClass4> testClass4List;

            public TestClass3() {
                testClass4List = new ArrayList<TestClass4>();
                testClass4List.add(new TestClass4());
                testClass4List.add(new TestClass4());
            }

            public Long getId() {
                return id;
            }
        }

3.  When your class is finished, you are ready to save your class and fetch it
    using an instance of its respective DAO.




