println
    def
        result: ref int = new 1
        x: int = 5
    in
        result := !result + x;
        !result
    end
;;
