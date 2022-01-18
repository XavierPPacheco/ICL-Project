def x:ref int =  new 0
s:ref int =  new 0 in
while !x <= 10 do
s := !s + !x ; x := !x + 1
end;
println !s
end;;