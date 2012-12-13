(defproject cweepy "0.1.0-SNAPSHOT"
  :description "ClojureScript example project."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [hiccups "0.1.1"]]
  :plugins [[lein-cljsbuild "0.2.9"]]
  :hooks [leiningen.cljsbuild]
  :source-paths ["src/clj"]
  :cljsbuild {
    :builds [
             {:source-path "src/cljs"
                :compiler {:output-to "resources/public/application.js"
                           :optimizations :whitespace
                           :pretty-print true}}
             {:source-path "src/cljs"
                :compiler {:output-to "resources/public/application.min.js"
                           :optimizations :advanced}}]})
