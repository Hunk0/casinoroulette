# Casino Roulette API
A rulette game REST API made with Java(Spring Framework) and MySql. First find the file 'application.properties' and set your own properties or you can set the environment variables as indicated.
### Endpoints:
<details>
<summary>1. Roulette creator</summary>
	URL : `/roulettes/newroulette`
    <br>
    Method : `POST`	
    <br>
    Response example :

    {
        "rouletteId": 12,
        "open": false,
        "bets": []
    }
</details>

<details>
	<summary>2. Open roulette</summary>
	URL : `/roulettes/{id}/open`
    <br>
    Method : `PUT`
    <br>
    Response example :     

    {
        "message": "La ruleta esta abierta, hagan sus apuestas!"
    }
</details>

<details>
	<summary>3. Make bet</summary>
	URL : `/roulettes/{id}/bet`
    <br>
    Method : `POST`
    <br>
    Notes : 
    <br/>
      <ul>
          <li>This Endpoint requires a 'userId' HEADER with a number in it.</li>
          <li>'value' property can be a number as string within 0 and 36 or color text ("negro" o "rojo")..</li>
      </ul>
    <br>
    Response example :     

    {
        "betId": 8,
        "userId": 6,
        "value": "36",
        "cash": 3000.0
    }
</details>

<details>
	<summary>4. Close roulette</summary>
	URL : `/roulettes/{id}/close`
    <br>
    Method : `PUT`
    <br>
    Response example :     

    {
        "message": "La ruleta se ha cerrado... El numero ganador ha sido el 9(color negro), gracias por participar!",
        "results": [
            {
                "betId": 1,
                "userId": 1,
                "value": "negro",
                "cash": 3600.0
            },
            {
                "betId": 9,
                "userId": 6,
                "value": "36",
                "cash": 0.0
            }
        ]
    }
</details>

<details>
	<summary>3. Roulette list</summary>
	URL : `/roulettes/{id}/bet`
    <br>
    Method : `GET`
    <br>
    Response example :     

    [
        {
            "rouletteId": 1,
            "open": true,
            "bets": []
        },
        {
            "rouletteId": 2,
            "open": true,
            "bets": [
                {
                    "betId": 2,
                    "userId": 6,
                    "value": "26",
                    "cash": 3000.0
                },
                {
                    "betId": 3,
                    "userId": 7,
                    "value": "rojo",
                    "cash": 3000.0
                }
            ]
        },
        {
            "rouletteId": 3,
            "open": false,
            "bets": []
        }
    ]
</details>
