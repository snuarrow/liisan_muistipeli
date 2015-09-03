testeillä olen pyrkinyt kattamaan ohjelmalogiikan kattavasti, rajatapauksia kokeillaan laajasti ja monelta kantilta. testauksesta löytyy automaattisesti generoitu pit raportti dokumentaatio/pit/ kansiosta.

	Luokkien testikattavuus:
		Radian
			-tarkistetaan että saa arvoja vain väliltä 0-2Pi
		PositionController
			-hitboksit liikkuvat kuten pitää
			-törmäykset 8 eri ilmansuunnassa toimivat
			-kimmotukset joka seinään toimivat
		Picture
			-kuva latautuu
			-mitat palauttavat oikeita arvoja
		HitBoxBoard
			-kortit piirretään matriisiin oikein
			-tarkistetaan että törmäys havaitaan oikein
		Global
			-setterit ja getterit toimivat
			-johdetut muuttujat päivittyvät asianmukaisesti
		Engine
			-luo kortteja
			-klikkaukset toimii
			-iteraatiossa tapahtuu jotain


graafista käyttöliittymää testit eivät kata, tähän minulla ei riittänyt vielä nouhau. sitä on kuitenkin kokeiltu parin viikon ajan ja 3.9 klo 1500 mennessä löytyneet bugit on korjattu.
