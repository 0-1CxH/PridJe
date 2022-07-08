import json
import PridJeJsonMXFuncReg

PridJeJsonMX_Function_Register_Table = dict()
scanned = False
RetPkgOnFailure = {"STA": False}


def scan_and_register_functions():
    # scan all the objects in PridJeJsonMXFuncReg, keep only callable non-magic objects in table
    global scanned
    global PridJeJsonMX_Function_Register_Table
    if scanned:
        return
    for obj_name in PridJeJsonMXFuncReg.__dir__():
        if not obj_name.startswith("__"):
            obj_ = PridJeJsonMXFuncReg.__getattribute__(obj_name)
            if "__call__" in obj_.__dir__():
                PridJeJsonMX_Function_Register_Table[obj_name] = obj_
    scanned = True
    return


def process_function(input_string):
    global scanned
    if not scanned:
        scan_and_register_functions()
    ret_pkg = process_function_json_multiplexer(input_string)
    return json.dumps(ret_pkg)


def process_function_json_multiplexer(input_string):
    global PridJeJsonMX_Function_Register_Table
    input_pkg = json.loads(input_string)
    func_name = input_pkg.get("FNM")
    input_args = json.loads(input_pkg.get("IN"))

    if func_name not in PridJeJsonMX_Function_Register_Table:
        # cannot find the function registered
        return RetPkgOnFailure
    func = PridJeJsonMX_Function_Register_Table.get(func_name)
    try:
        ret = func(**input_args)
    except Exception as e:
        print(e)
        return RetPkgOnFailure
    if not isinstance(ret, dict):
        return RetPkgOnFailure
    try:
        output_value = json.dumps(ret)
    except Exception as e:
        print(e)
        return RetPkgOnFailure

    return {"STA": True, "OUT": output_value}



