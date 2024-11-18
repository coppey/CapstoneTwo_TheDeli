# Subwayzz -
## The Sandwich Order Management System

The Deli Order Management System is a simple program that helps users create and customize their deli orders step by step. You can build a sandwich by choosing the type of bread, size, toppings, and whether it’s toasted. You can also add drinks and chips to your order. Once your order is complete, you can confirm it and save a receipt with all the details.

## Features
- Error Handling
- Ease of use
- Fairly Organized

## How it Works
1. The program initializes the home menu and allows the user to create a new order or exit
2. When a new order is started:
    - The user's name is captured
    - The user can add sandwiches, drinks, and chips to the order
3. Sandwich customization allows adding, viewing, or removing toppings
4. While Checking out:
    - The order details are displayed
    - The user can confirm or cancel the order
5. Confirming the order generates a receipt in the receipts folder

## Walkthrough
When the program starts, you will see the following menu:
![FirstOptionSc.PNG](src%2Fmain%2Fresources%2FFirstOptionSc.PNG)
---
After selecting **Start a New Order** the program will prompt you to enter your name:
![SecondOpAndName.PNG](src%2Fmain%2Fresources%2FSecondOpAndName.PNG)
---
After Putting a Name for your Order you will be Presented with these options
![3SubMenu.PNG](src%2Fmain%2Fresources%2F3SubMenu.PNG)
---
Clicking 1-3 will send you to a number of Sub Menus allowing you to customize the **Product** you're Adding to your **Order**
![FourthCheckout.PNG](src%2Fmain%2Fresources%2FFourthCheckout.PNG)
---
When you're finished, You can Click "0/**Cancel**" to erase it all, or "4/**Checkout**" to Continue to Checkout and Reciept printing

When you select **Checkout** the program will display your order details and ask for confirmation:
![fithConfirm.PNG](src%2Fmain%2Fresources%2FfithConfirm.PNG)
---
Confirming will Generate your **receipt** and save it to the Receipt folder
![sixthReciept.PNG](src%2Fmain%2Fresources%2FsixthReciept.PNG)
---
## Interesting Code - **DefaultTopping**
- The **DefaultTopping** Class was Created to better handle **Toppings** that didn't affect the Price of the **Sandwich**

```java 
public class DefaultTopping extends Topping{

public DefaultTopping(String name) {
  super(name, 0);
  }

  @Override
  public String getType() {
  return "Default";
    } 
  }
```
- This allows me to better Categorize, Organize, and Assign Toppings
```java
sandwich.addTopping(new Cheese(cheeseName, sandwich.getRegularCheesePrice()), false);

sandwich.addTopping(new DefaultTopping("Lettuce"), false);


```