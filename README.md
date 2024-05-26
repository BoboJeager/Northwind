# Northwind Backend (Product and Order)
Question 2b creating a crud application
## Technology
Spring boot(JDBC, JPA(ORM), Lombok , Spring web) using MAVN and Docker \
Database: mySQL using a docker image (PORT: 3308) //3306 was used on my machine

## Start up
docker-compose up --build \
run Interview Test Applications

## URLs
(GET,POST) \
/api/products \
(GET,PATCH,DELETE) \
/api/products/{id} \
(GET, POST) \
/api/customers \
(GET,PATCH,DELETE) \
/api/customers/{id} \
(GET add different countries seperated by "/") \
api/customers/countries/USA \
##Test JSON if needed

### Customers
```json
[
	{
		"customerId": 4,
		"companyName": "Acme Corporation",
		"contactName": "John Doe",
		"contactTitle": "Procurement Manager",
		"address": "123 Elm St",
		"city": "Springfield",
		"region": "IL",
		"postalCode": null,
		"country": "USA",
		"phone": "555-1234",
		"fax": "555-5678"
	},
	{
		"customerId": 5,
		"companyName": "Globex Corporation",
		"contactName": "Jane Smith",
		"contactTitle": "Chief Technology Officer",
		"address": "456 Maple Avenue",
		"city": "Hill Valley",
		"region": "CA",
		"postalCode": "90382",
		"country": "USA",
		"phone": "555-9876",
		"fax": "555-6543"
	}
]
```
### Products
```json
{
    "productName": "Green Tea",
    "supplierId": 2,
    "categoryId": 1,
    "quantityPerUnit": "10 boxes x 20 bags",
    "unitPrice": 19.50,
    "unitsInStock": 150,
    "unitsOnOrder": 25,
    "reorderLevel": 10,
    "discontinued": false
}

```
