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
   :final-price discounted-price})

(discount2 50)


(defn discount3 [price]
  (let [discount-amount (/ (* price 20)
                           100)
        discounted-price  (- price discount-amount)]
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

(type retail-products)
(count retail-products)



(get retail-products 0)
(type (get retail-products 0))
(keys (get retail-products 0))

(get [7 8 9 -8 false true "I like bananas" nil {:name "pinchi"} [7 7 7] 3 2 1] 0)
(count [7 8 9 -8 false true "I like bananas" nil {:name "pinchi"} [7 7 7] 3 2 1])
(last [7 8 9 -8 false true "I like bananas" nil {:name "pinchi"} [7 7 7] 3 2 1])
(get [7 8 9 -8 false true "I like bananas" nil {:name "pinchi"} [7 7 7] 3 2 1] 100)

(get retail-products 0)
(:price (get retail-products 0))

(map :price retail-products)
(apply max (map :price retail-products))

(discount (:price (get retail-products 0)))

(defn change-product-price [p]
       (let [price (:price p)
             discounted-price (discount price)]
         ;discounted-price
         (assoc p :price-whole-sale discounted-price)
         ))

(change-product-price (get retail-products 0))

(map change-product-price retail-products)













; show me the wholesale price list 
; (all products, but the price being discounted by 20%)
