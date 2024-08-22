package com.murali.learn.moviecatalogsystem.module;

import java.util.List;

public class CatalogItem {
List<Catalog> listOfCatalog;

public List<Catalog> getListOfCatalog() {
	return listOfCatalog;
}

public void setListOfCatalog(List<Catalog> listOfCatalog) {
	this.listOfCatalog = listOfCatalog;
}

public CatalogItem(List<Catalog> listOfCatalog) {
	super();
	this.listOfCatalog = listOfCatalog;
}

public CatalogItem() {
	super();
}

}
