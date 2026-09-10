(ns culture.facts
  "Country-level regional-culture catalog for Czechia (CZE) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"CZE"
   [{:culture/id "cze.dish.svickova"
     :culture/name "Svíčková"
     :culture/name-local "Svíčková na smetaně"
     :culture/country "CZE"
     :culture/kind :dish
     :culture/summary "Czech meat dish of beef with root vegetables and cream sauce, one of the most common Czech and Slovak meals."
     :culture/url "https://en.wikipedia.org/wiki/Sv%C3%AD%C4%8Dkov%C3%A1"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cze.dish.vepro-knedlo-zelo"
     :culture/name "Vepřo knedlo zelo"
     :culture/country "CZE"
     :culture/kind :dish
     :culture/summary "Roast pork with dumplings and sauerkraut, one of the national dishes of the Czech Republic; believed to have German origins and also commonly eaten in Slovakia and Austria."
     :culture/url "https://en.wikipedia.org/wiki/Vep%C5%99o_knedlo_zelo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cze.dish.knedliky"
     :culture/name "Knedlíky"
     :culture/country "CZE"
     :culture/kind :dish
     :culture/summary "Boiled dumplings made from potatoes, bread or flour, used in various dishes in Czech cuisine as well as Austrian, German and Slovak cooking."
     :culture/url "https://en.wikipedia.org/wiki/Kn%C3%B6del"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cze.beverage.pilsner-urquell"
     :culture/name "Pilsner Urquell"
     :culture/country "CZE"
     :culture/kind :beverage
     :culture/summary "The world's first pale lager, first brewed in 1842 in Plzeň; most pale lagers produced worldwide are based on it."
     :culture/url "https://en.wikipedia.org/wiki/Pilsner_Urquell"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cze.beverage.becherovka"
     :culture/name "Becherovka"
     :culture/country "CZE"
     :culture/kind :beverage
     :culture/summary "Herbal bitters liqueur traditionally served as a digestif, produced in Karlovy Vary, Czech Republic by the Jan Becher company."
     :culture/url "https://en.wikipedia.org/wiki/Becherovka"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cze.product.olomoucke-tvaruzky"
     :culture/name "Olomoucké tvarůžky"
     :culture/country "CZE"
     :culture/kind :product
     :culture/summary "Ripened soft cheese made in Loštice, Olomouc Region, Czech Republic, first mentioned in writing in 1452; registered as an EU Protected Geographical Indication since 2010."
     :culture/url "https://en.wikipedia.org/wiki/Olomouck%C3%A9_tvar%C5%AF%C5%BEky"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cze.craft.bohemian-glass"
     :culture/name "Bohemian glass"
     :culture/name-local "České sklo"
     :culture/country "CZE"
     :culture/kind :craft
     :culture/summary "Glass produced in the regions of Bohemia and Silesia, now parts of the Czech Republic, renowned for its quality and craftsmanship."
     :culture/url "https://en.wikipedia.org/wiki/Bohemian_glass"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cze.heritage.cesky-krumlov"
     :culture/name "Český Krumlov"
     :culture/country "CZE"
     :culture/kind :heritage
     :culture/summary "Town in the South Bohemian Region of the Czech Republic, a designated UNESCO World Heritage Site since 1992 for its well-preserved Gothic, Renaissance and Baroque architecture."
     :culture/url "https://en.wikipedia.org/wiki/%C4%8Cesk%C3%BD_Krumlov"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-cze culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "CZE"))
                 " CZE entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
