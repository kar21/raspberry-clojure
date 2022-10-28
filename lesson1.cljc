

; ******************** numbers - mathematics 

; + is the symbol of a function
(+ 1 2)
(+ 3 1 2)


(* 1310 790)

(* 5 8)

(* 5 (+ 7  3))

(* 5 (+ 7  3) 2)

66

;  *********************** string  text

"isa"
(def i "isa")
i

(def j "joseph")

(def c "chris")


(str i j c)

(subs j 2)

; ******************* bool - true/false

true
false

(def end-of-world? false)

(not end-of-world?)



; *************  map - a bag of values, each with a name to find it.

{:first-name "joseph"
 :last-name "ylles"}
(def joseph {:first-name "joseph"
             :last-name "ylles"})
joseph
joseph2
(def isa {:first-name "isa" :last-name "valdes"})

isa
{:first-name "crist" :last-name "mendoza"}
(:last-name isa)

(assoc {:first-name "chris"} :superpower "guitar")

(def joseph2
(assoc joseph :superpower "painting and kambo"
       :pet "punchi") 
  )

(dissoc joseph2 :pet)


; **************** vector/list - a list of values (indexed by a position number)

["red" "green" "blue"]

(get ["red" "green" "blue"] 0)
(get ["red" "green" "blue"] 1)
(get ["red" "green" "blue"] 2)
(get ["red" "green" "blue"] 3)


(def partipantes [isa joseph2 {:first-name "crist" :last-name "mendoza"}])
(get partipantes 0)

(:last-name (get partipantes 0))
(map :last-name partipantes)

(map :superpower partipantes)

(count partipantes)
(count [7 8 9 55 "ayuahaska" :kambo])

[i j c]


(get [i j c] 1)   ; 0 is the first position.



