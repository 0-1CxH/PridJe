package pers.h01c.pridjemultiplex;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import pers.h01c.pridjecore.PridJeCoreEntry;

public class PridJeJsonMultiplexer {
    PridJeCoreEntry core;
    public PridJeJsonMultiplexer(PridJeCoreEntry core){
        this.core = core;
    }
    public JSONObject CallRegisteredFunction(String funcName, JSONObject inputArgs) throws PridJeCoreEntry.PridJeCoreException {
        JSONObject pkg = new JSONObject();
        pkg.put("FNM", funcName);
        pkg.put("IN", inputArgs.toString());
        String retString = core.CallProcessFunction(pkg.toString());
        JSONObject retPkg = JSON.parseObject(retString);
        Boolean isSuccessful = retPkg.getBoolean("STA");
        if(!isSuccessful){return null;}
        String outputs = retPkg.getString("OUT");
        if(outputs==null){return null;}
        return JSON.parseObject(outputs);
    }
}
