package pers.h01c.pridjecore;

public class PridJeCoreEntry {
    static {System.loadLibrary("PridJeCore");}

    private final boolean coreStarted;

    public PridJeCoreEntry() throws PridJeCoreException{
        int coreStartingValue = InitPridJeCore();
        if(coreStartingValue<=0){
            throw new PridJeCoreException(coreStartingValue);
        }
        coreStarted=true;
    }

    public String CallProcessFunction(String inputString) throws PridJeCoreException {
        String ret = ProcessFunction(inputString);
        if(ret==null){
            throw new PridJeCoreException(0);
        }
        else{
            return ret;
        }
    }

    protected void finalize(){if(coreStarted){UnInitPridJeCore();}}

    native private int InitPridJeCore(); // return 1 if initialized successfully
    native private void UnInitPridJeCore(); // stop PridJeCore
    native private String ProcessFunction(String s); // the function to implement in Python

    static public class PridJeCoreException extends Exception{
        static final String[] errorMessage = {
                "Process Function Execution Error",
                "Python Interpreter Failed to Start",
                "Process Function Load Error",
                "PridJeCore Portal Module Load Error"
        };

        private final String errMsg;
        public PridJeCoreException(int err){
            errMsg = errorMessage[-err];
        }
        public String getErrorMessage(){
            return errMsg;
        }

    }

}
