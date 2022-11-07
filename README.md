# Meetup API


## Installation

---
1. Download this project to your local machine: 
`$ git clone https://github.com/labovichl333/Meetup.git`
2. Run `$ mvn install` from the root folder of the downloaded project
3. Run `$ docker compose up`
4. Start to send requests to the endpoints
### Endpoints

| Route                     | HTTP     | Description                       |
|---------------------------|----------|-----------------------------------|
| **/api/v1/meetups**       | `GET`    | Get all meetups                   |
| **/api/v1/meetups/{id}**  | `GET`    | Get meetup by `id`                |
| **/api/v1/meetups**       | `POST`   | Create meetup                     |
| **/api/v1/meetups/{id}**  | `DELETE` | Delete meetup by `id`             |
| **/api/v1/meetups/{id}**  | `PUT`    | Update data of the meetup by `id` |


### Note

---

 **/api/v1/meetups** `GET` endpoint you can use by 
 combining any of these parameters: `topic`, `organizer`, `startTime`, `endTime` 
 to filter meetups.
 
To set the sorting order you should add parameter `sort`.
 
Parameter `sort` include `key:value` expressions separated by `,` 

The following parameters can be used as the key :
1. **topic**
2. **organizer**
3. **scheduledTime**

The following parameters can be used as the values :
1. **asc**
2. **desc**

**`The order of sort expressions affects the result`**

---