# Supermarket - CRUD

A supermarket Java API that can do a C.R.U.D.

# Entitys: 
## Product
    - product_id REQUIRED
    - name REQUIRED
    - price REQUIRED
    - quantity REQUIRED
    - description

## Cliente
    - client_id REQUIRED
    - name REQUIRED
    - email REQUIRED
    - password REQUIRED

## Cart
    - cart_id REQUIRED
    - client_id REQUIRED
    - product_id REQUIRED

# Business Rules for MVP
## User
    - Since theres no real password or user, you can get a list of all users without having admin.
    - The app will not have a login/authentication system, but you can register a new user and get his Id. So the email and password are only examplarys.
    - A user can only have one cart.
    - Password must be atleast 6 characters length.
    - Email must have one "@" and ".com".
    - Name must be atleast 3 character length.
    - Since is a training API it will not utilize password encryption.
##  Products
    - Anyone can insert new products to the database.
    - Products that reach 0 quantity must be deleted.
    - A user cannot buy more than the product quantity.
## Shopping Cart
    - Id of cart item is created by database (incremental)
    - Its a referencial 

# Methods
## GET /products/productList/
    - List all products avaibles.
    - Param: int "pageNumber"
    - If no "pageNumber" is informed, system wil default choose "0"
    - Offset: 10.

## GET /user/cartlist/
    - List all products avaibles.
    - Param: int "pageNumber"
    - If no "pageNumber" is informed, system wil default choose "0"
    - Offset: 10.

## POST /user/create/
    - Creates a new user.
    - Returns "succesful" or "failed to create new user"

## POST /products/create/
    - Creates new product.
    - Description is optional.
    - Params must come from body.

## POST /cart/insertproduct/
    - Insert a new product associated with a user.

## DELETE /user/remove/
    - Deletes user form database.
    - User password must be informed to delete the user.

## DELETE /cart/remove/
    - Deletes user form database.
    - User password must be informed to delete the user.

