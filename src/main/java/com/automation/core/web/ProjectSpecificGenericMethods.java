package com.automation.core.web;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProjectSpecificGenericMethods {

	static String PROJECT_ROOT_FOLDER = "..//Automation";
	public WebDriver driver;
	List<WebElement> elements;
	WebElement element;
	GenerticMethods objGenerticMethods;
	
	public ProjectSpecificGenericMethods(WebDriver driver) {
		this.driver = driver;
		objGenerticMethods = new GenerticMethods(this.driver);
	}
	
	public List<WebElement> getCatagoryCheckBoxList()
	{
		List<WebElement> elements = objGenerticMethods.getFindElements("xpath", ".//*[@class='categories-list']/li");
		return elements;
	}
	
	public WebElement getCatagoryCheckBoxListByName(String itemName)
	{
		elements = getCatagoryCheckBoxList();
		return objGenerticMethods.getWebElementByName(elements, itemName); 
	}
	

	public List<WebElement> getBrandCheckBoxList()
	{
		List<WebElement> elements = objGenerticMethods.getFindElements("xpath", ".//*[@class='brand-list']/li");
		return elements;
	}
	
	public WebElement getBrandCheckBoxListByName(String itemName)
	{
		elements = getBrandCheckBoxList();
		return objGenerticMethods.getWebElementByName(elements, itemName); 
	}
	
	public WebElement getBrandMoreLink()
	{
		return objGenerticMethods.getfindElement("class", "brand-more");
	}

	public List<WebElement> getPriceCheckBoxList()
	{
		List<WebElement> elements = objGenerticMethods.getFindElements("xpath", ".//*[@class='price-list']/li");
		return elements;
	}
	
	public WebElement getPriceCheckBoxListByName(String itemName)
	{
		elements = getPriceCheckBoxList();
		return objGenerticMethods.getWebElementByName(elements, itemName); 
	}
	
	public List<WebElement> getColourCheckBoxList()
	{
		List<WebElement> elements = objGenerticMethods.getFindElements("xpath", ".//*[@class='colour-list']/li");
		return elements;
	}
	
	public WebElement getColorCheckBoxListByName(String itemName)
	{
		elements = getColourCheckBoxList();
		return objGenerticMethods.getWebElementByName(elements, itemName); 
	}
	
	public List<WebElement> getDiscountCheckBoxList()
	{
		List<WebElement> elements = objGenerticMethods.getFindElements("xpath", ".//*[@class='discount-list']/li");
		return elements;
	}
	
	public WebElement getDiscountCheckBoxListByName(String itemName)
	{
		elements = getDiscountCheckBoxList();
		return objGenerticMethods.getWebElementByName(elements, itemName); 
	}
	
	public WebElement getColorMoreLink()
	{
		return objGenerticMethods.getfindElement("class", "colour-more");
	}
	
	public WebElement getHorizontalFiltersTitle()
	{
		return objGenerticMethods.getfindElement("class", "horizontal-filters-title");
	}
	
	public WebElement getHorizontalFiltersSub()
	{
		return objGenerticMethods.getfindElement("class", "horizontal-filters-sub");
	}
	
	
	public List<WebElement> getContentPageFiltersLabelList()
	{
		List<WebElement> elements = objGenerticMethods.getFindElements("xpath", ".//*[@class='atsa-filters']/li");
		return elements;
	}
	
	public WebElement getContentPageFiltersLabelListByName(String itemName)
	{
		elements = getContentPageFiltersLabelList();
		return objGenerticMethods.getWebElementByName(elements, itemName); 
	}
	
	
	public WebElement getSoryBy()
	{
		return objGenerticMethods.getfindElement("class", "sort-sortBy");
	}
	
	public List<WebElement> getSearchResultsList()
	{
		List<WebElement> elements = objGenerticMethods.getFindElements("xpath", ".//*[@class='product-base']");
		return elements;
	}
	
	public WebElement getSearchResultsListByName(String itemName)
	{
		elements = getSearchResultsList();
		return objGenerticMethods.getWebElementByName(elements, itemName); 
	}
	
	public WebElement getSpecificDetailsFromSelectedProduct(WebElement Product, String SpecificProductDetail)
	{
		//product-thumb
		//product-brand
		//product-product ()
		//product-discountedPrice
		// product-strike --original price
		// product-discountPercentage 
		
		if(SpecificProductDetail.equalsIgnoreCase("ProductThumb"))
		{
//			element =  Product.findElement(By.className("product-thumb"));		
			element = objGenerticMethods.getfindElement("xpath", ".//*[@class='product-thumb']");
			
		}else if(SpecificProductDetail.equalsIgnoreCase("ProductBrand"))
		{
//			element =  Product.findElement(By.className("product-brand"));
			element = objGenerticMethods.getfindElement("xpath", ".//a/img/..//div[@class='product-productMetaInfo']/div[@class='product-brand']");
		}else if(SpecificProductDetail.equalsIgnoreCase("ProductType"))
		{
//			element =  Product.findElement(By.className("product-product"));
			element = objGenerticMethods.getfindElement("xpath", "/a/img/..//div[@class='product-productMetaInfo']/*[@class='product-product']");
		}else if(SpecificProductDetail.equalsIgnoreCase("DiscountedPrice"))
		{
//			element =  Product.findElement(By.className("product-discountedPrice"));
			element = objGenerticMethods.getfindElement("xpath", "/a/img/..//div[@class='product-productMetaInfo']/div[@class='product-price']/span/span[@class='product-discountedPrice']");
		}else if(SpecificProductDetail.equalsIgnoreCase("StrikePrice"))
		{
//			element =  Product.findElement(By.className("product-strike"));
			element = objGenerticMethods.getfindElement("xpath", "/a/img/..//div[@class='product-productMetaInfo']/div[@class='product-price']/span/span[@class='product-strike']']");
		}else if(SpecificProductDetail.equalsIgnoreCase("DiscountPercentage"))
		{
//			element =  Product.findElement(By.className("product-discountPercentage"));
			element = objGenerticMethods.getfindElement("xpath", "/a/img/..//div[@class='product-productMetaInfo']/div[@class='product-price']/span[@class='product-discountPercentage']");
		}
		
		return element;
		
	}
	
	
	
	
	
}
