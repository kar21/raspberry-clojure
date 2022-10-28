

; ******************** numbers - mathematics 

; + is the symbol of a function
(+ 1 2)
(+ 3 1 2)


(* 1310 790)

(* 5 8)

(* 5 (+ 7  3))

(* 5 (+ 7  3) 2)

;  *********************** string  text

"isa"
(def i "isa")
(def j "joseph")
(def c "chris")

(str i j c)


; ******************* bool - true/false

true
false

(def end-of-world? false)

(not end-of-world?)

(def sunny? false)
(def raining? true)




; *************  map - a bag of values, each with a name to find it.

{:first-name "joseph"
 :last-name "ylles"}


(assoc 
 {:first-name "joseph"
  :last-name "ylles"}
 :superpower
 "painting and kambo"
 )

; **************** vector/list - a list of values (indexed by a position number)

["red" "green" "blue"]
(count [7 8 9 55 "ayuahaska" :kambo])

[i j c]


(get [i j c] 1)   ; 0 is the first position.



