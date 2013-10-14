GLO4003
=======

Website of the Rouge et Or sports teams of Laval University (Québec City).
This project is part of GLO-4003 class.

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
    
    If you want to serialize a list of models use these two annotations.
    
        @XStreamImplicit(itemFieldName="TestClass4")
    	@XStreamConverter(XmlObjectConverter.class)
        
    itemFieldName is the name of your serialized class.
    
    This is an example of how it works.
    
        private static class TestClass3 implements ModelInterface {
        	long id = 1;
    		String test1 = "test1";
    		String test2 = "test2";
    		
    		public TestClass3() {
    			testClass4List = new ArrayList<TestClass4>();
    			testClass4List.add(new TestClass4());
    			testClass4List.add(new TestClass4());
    		}
            
    		@XStreamImplicit(itemFieldName="TestClass4")
    		@XStreamConverter(XmlObjectConverter.class)
    		List<TestClass4> testClass4List;
    		
    		public Long getId() {			
    			return id;
    		}		
    	}
        
3.  When your class is finished, you are ready to save your class and fetch it
    using an instance of its respective DAO.

    
    
    
    
    
    