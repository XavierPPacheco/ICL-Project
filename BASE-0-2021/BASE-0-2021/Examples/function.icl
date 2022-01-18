fun	exp(base:int, exp:int):int =
    def result: ref int = new 1
    in
        if !base < 0 || !exp < 0 then
            0
        else
            if !exp == 0 then
                1
            else
                while !exp != 0 do
                    result := !result * !base;
                    exp := !exp - 1
                end;
                !result
            end
        end
    end
end;;

println exp(1, 2);;
println exp(3, 4);;
