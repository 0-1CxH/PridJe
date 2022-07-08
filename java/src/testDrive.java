import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import pers.h01c.pridjecore.PridJeCoreEntry;
import pers.h01c.pridjemultiplex.PridJeJsonMultiplexer;

public class testDrive {
    public static void main(String[] args) {

        // testCore();
        testJsonMx();

    }

    static void testCore(){
        PridJeCoreEntry c = null;
        try {
            c = new PridJeCoreEntry();
        } catch (PridJeCoreEntry.PridJeCoreException e) {
            System.out.println(e.getErrorMessage());
        }
        String ret = null;
        try {
            ret = c.CallProcessFunction("hell1o");
            System.out.println(ret);
        } catch (PridJeCoreEntry.PridJeCoreException e) {
            System.out.println(e.getErrorMessage());
        }
    }

    static void testJsonMx(){
        PridJeCoreEntry c = null;
        try {
            c = new PridJeCoreEntry();
        } catch (PridJeCoreEntry.PridJeCoreException e) {
            System.out.println(e.getErrorMessage());
        }
        PridJeJsonMultiplexer jm = new PridJeJsonMultiplexer(c);
        JSONObject ret = null;
        try {
            JSONObject inputArg = new JSONObject();
            inputArg.fluentPut("a", 1).fluentPut("b", 2);
            ret = jm.CallRegisteredFunction("func1", inputArg);
        } catch (PridJeCoreEntry.PridJeCoreException e) {
            System.out.println(e.getErrorMessage());
        }
        System.out.println(ret);
    }
}
