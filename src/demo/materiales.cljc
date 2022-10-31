(ns demo.materiales)


(def price 200)
price
(* price 20)

(/ (* price 20) 100)
(- price (/ (* price 20) 100))


(defn discount [price] 
  (- price (/ (* price 20) 100)))


(discount 2000)
(discount 50)


(defn discount2 [price]
  (def discount-amount (/ (* price 20) 100)) 
  (def  discounted-price  (- price discount-amount))
    ;discounted-price
 ; [price discount-amount discounted-price]
  {:price price 
   :discount discount-amount
   :final-price discounted-price}
  )

(discount2 50)


(defn discount3 [price]
  (let [discount-amount (/ (* price 20)
                           100)
        discounted-price  (- price discount-amount)
        ]
    discounted-price))

(discount3 2000)






; write a function that gives me the discounted price (p=price d=discount.
; example: price: 100. discount: 20. discounted price: 80.

(def retail-products
  [{:name "baldosas azul"
    :price 18.99
    :dimension "m2"}
   {:name "inodoro blanco"
    :price 124.99
    :dimension "piece"}
   {:name "llavamano lujo"
    :price 155
    :dimension "piece"}])




; show me the wholesale price list 
; (all products, but the price being discounted by 15%)
