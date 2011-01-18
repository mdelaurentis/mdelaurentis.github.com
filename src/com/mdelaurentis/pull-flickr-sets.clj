(ns pull-flickr-sets
  (:use [hiccup page-helpers core]

        [somnium.congomongo coerce])
  (:require [clojure.contrib.io :as io]))


#_(def url "http://api.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=c452700c9cab518321bb02f2ef1fb8f6&photoset_id=&auth_token=72157625703135291-ecec53e5f622b266&api_sig=6fcf31ce62e0370b7d933e20c6f2ea35")

(def flickr-key "9de35096dd63e2021d7def27a3365beb")
(def flickr-secret "5684d5cf0167bc63")

(defn photo-to-html [p]
  (html [:a {:href (format "http://www.flickr.com/photos/mdelaurentis/%s"
                           (:id p))}
         [:img {:style "padding-left: 1em;"
                :src (format "http://farm%s.static.flickr.com/%s/%s_%s_t.jpg"
                             (:farm p)
                             (:server p)
                             (:id p)
                             (:secret p))}]]))

(defn get-photos-in-set [set]
  (let [url (url  "http://api.flickr.com/services/rest"
                  {:method "flickr.photosets.getPhotos",
                   :api_key flickr-key,
                   :photoset_id set,
                   :format "json"})
        json (io/slurp* url)
        [_ contents] (re-matches #"jsonFlickrApi\((.*)\)" json)
        data     (coerce contents [:json :clojure])
        photos (:photo (:photoset data))]
    (map photo-to-html photos)))

(def photo-sets
  {:trail "72157625750745898",
   :woods  "72157625625222491",
   :creek "72157625750837878"
   :field "72157625750909834",
   :river "72157625751042286",
   :panoramas "72157625828903490"})


(println "Here I am")
(doseq [link (get-photos-in-set (photo-sets :panoramas))]
  (println link))

