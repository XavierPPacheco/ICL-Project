def
        m: ref int = new 10
    in
        while !m>9 do
            println !m ;
            if !m / 2 > 2 then
                m := !m / 2
            else
                m := 3*!m + 1
            end
        end
    end
;;
