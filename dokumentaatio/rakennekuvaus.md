ohjelma koostuu kahdesta paketista, ohjelmalogiikasta ja käyttöliittymästä.

kun peli käynnistetään, latadataan kortit satunnaisesti noin 500 kuvan kannasta. pelin ei pitäisi olla millään pelikerralla täysin samanlainen.

ohjelmalogiikka hallitsee korttien sijaintiin, törmäilyyn ja kuvien näyttämiseen tarvittavaa tietoa. jokaisella kortilla on liukulukukoordinaatit ja näiden koordinaattien mukaan päivitetään nollamatriisiin(hitboxboard) kortin id:tä onton neliön muotoisiin kuvioihin. kun kortti liikkuu, piirretään hitboxboardiin aina neliö eri kohtaan, kun neliöt kohtaavat sattuu törmäys joka käsitellään eri tavoin riippuen kortin sijainnista. kun korttia klikataan, annetaan kyseiselle kortille satunnainen kulma väliltä 0-360 astetta sekä vakio aloitusnopeus. käyttöliittymän timer triggeröi ohjelmalogiikan yhden iteraation jolloin käydään jokainen kortti läpi ja liikutellaan niitä mikäli liikettä on.

käyttöliittymä saa engine luokalta esim korttien sijaintitiedot sekä korttien kuvat. Käyttöliittymä toimii tilakoneen tavoin, tila 0 on alkuvalikko, 1 pelitila, 2 asetusvalikon tila, 3 loppuvalikon tila. Myös valikoiden nappien painallukset hallitaan samanlaisella tyylillä. Toteutusmuoto ei ehkä ole kaikista järkevin ja useassa paikassa olisi viilattavaa. hiiren sijaintia tarkkaillaan jatkuvasti ja kun se saavuttaa jonkin napin, ilmoitetaan tämä käyttäjälle napin värin vaihtumisena. pelitilassa piirretään jokaisella timerin tickillä kortit uudestaan.


havaittuja bugeja:
	-välillä kun klikkaa taustaan kortin klikkaamisen jälkeen, printtaa ohjelma poikkeuksen.
	-korttien kimpoaminen ei aivan noudata fysiikan lakeja ja on ehkä joskus hieman tökerön näköistä

havaittuja epäkohtia:
	-valikkoluokat olisi voinut sulauttaa yhdeksi
	-hitboksit toteuttaa korttien etäisyyksiä vertailemalla
	-pelin voi malttamaton pelata läpi, ilman että kortit alun jälkeen siirtyvät
	-peli on raskas
	
jatkokehitysideoita:
	-kun korttia klikkaa, näytettäisiin zoomautuvaa kuvaa niin kauan kuin hiiri on pohjassa, ja tämä painallusaika korreloisi kortin lähtönopeuteen, näin saataisiin hieman interaktiivisuutta lisää.
