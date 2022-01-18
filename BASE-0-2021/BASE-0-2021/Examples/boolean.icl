println
    def
        result: ref bool = new true
        constant1: int = 1
        constant2: int = 2
    in
        if (constant2 > constant1) then
            result := ~(!result)
          else
           result := !result
        end;
        !result
    end
;;
