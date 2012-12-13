(ns cweepy.core
  (:require-macros [hiccups.core :as hiccups])
  (:require [hiccups.runtime :as hiccupsrt])
  (:use [clojure.browser.dom :only [get-element]]
        [clojure.browser.event :only [listen]]))

(defn render-cweeps [cweeps]
  (let [cweep-list (get-element "cweep-list")]
    (set! (.-innerHTML cweep-list)
      (hiccups/html [:ul (for [cweep cweeps] [:li
                      [:div {:class "avatar"
                             :style (str "background: url(" (:avatar cweep) "); background-size: 40px 40px;")} ""]
                      [:div {:class "message"} (:message cweep)]])]))))

(def cweeps (atom []))

(add-watch cweeps :add-cweep (fn [x y a b] (render-cweeps b)))

(def xhr-stub [
  { :avatar "https://si0.twimg.com/profile_images/2536088319/4sl2go65was3o0km520j_reasonably_small.jpeg",
    :message "I love ActionScript" }
  { :avatar "https://si0.twimg.com/profile_images/2201732897/notch_weird.png",
    :message "Endermen used to be people" }
  { :avatar "https://si0.twimg.com/profile_images/1968705093/avatar.jpg",
    :message "dat cweep" }
  { :avatar "https://si0.twimg.com/profile_images/2536088319/4sl2go65was3o0km520j_reasonably_small.jpeg",
    :message "We do not go to Ravenholm" }])

(defn add-cweep []
  (let [new-message (.-value (get-element "add-cweep-message"))]
    (swap! cweeps (fn [] (conj (deref cweeps)
      { :avatar "https://si0.twimg.com/profile_images/2536088319/4sl2go65was3o0km520j_reasonably_small.jpeg",
        :message new-message })))))

(defn show-add-cweep-form []
  (set! (.-display (.-style (get-element "add-cweep-form"))) "block"))

(listen (.getElementById js/document "add-cweep-submit") :click add-cweep)
(listen (get-element "add-cweep") :click show-add-cweep-form)

(defn main [] (swap! cweeps (fn [] xhr-stub)))
