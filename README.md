# Android_GeoLoc

Zadanie polega na stworzeniu aplikacji, która będzie umożliwiała otrzymywanie najbardziej aktualnych współrzędnych miejsca, w którym się w tym czasie znajdujemy, z możliwością dodania go wraz z opisem do listy miejsc odwiedzonych. Dodatkowo powinno być możliwe wyświetlenie wszystkich miejsc na mapie wraz z ich wskaźnikami (markers). W przypadku wkroczenia lub opuszczenia terenu (np. 100 metrów wokół współrzędnych), aplikacja powinna informować o tym fakcie poprzez tworzenie notyfikacji.

 Wymagania:

1) 2 widoki:
	Widok A: Mapa z wyznaczonymi odwiedzonymi miejscami.
	Widok B: Składa się z 3 TextView i EditText (nazwa, opis miejsca, promień jaki obejmuje) oraz przycisku do dodania miejsca, w którym się znajdujemy do listy odwiedzonych. [4 pkt]

2) Lista odwiedzonych miejsc powinna się składać z nazwy, opisu, promienia oraz współrzędnych geograficznych. Powinna także być zapisywana i dostępna po kolejnym uruchomieniu aplikacji. Nie ma wymagań, co do użytej metody zapisu (SQLite, zwykły plik, chmura itp.). [3 pkt]

3) Aplikacja powinna notyfikować o wkroczeniu lub opuszczeniu dowolnego z miejsc (proximity alert lub Geofence). [3 pkt]

	Łączna liczba punktów do uzyskania: 10
