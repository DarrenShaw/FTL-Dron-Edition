package gameplay;
public class ThreadFixer{
    public ThreadFixer()
    {
        if(System.getProperty("os.name").startsWith("Win")) 
        {   new Thread() 
            {      
                { 
                    setDaemon(true);
                    start();     
                }            
                public void run() 
                {        
                    while(true) 
                    {           
                        try 
                        { 
                            Thread.sleep(Long.MAX_VALUE);
                        }            
                        catch(Exception exc) {}
                    }     
                } 
            };
        } 
    }
}