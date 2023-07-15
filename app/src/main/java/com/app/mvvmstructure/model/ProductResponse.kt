package com.app.mvvmstructure.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(

	@SerializedName("total")
	val total: Int? = null,

	@SerializedName("limit")
	val limit: Int? = null,

	@SerializedName("skip")
	val skip: Int? = null,

	@SerializedName("products")
	val products: List<ProductsItem?>? = null
)

data class ProductsItem(

	@SerializedName("discountPercentage")
	val discountPercentage: Any? = null,

	@SerializedName("thumbnail")
	val thumbnail: String? = null,

	@SerializedName("images")
	val images: List<String?>? = null,

	@SerializedName("price")
	val price: Int? = null,

	@SerializedName("rating")
	val rating: Any? = null,

	@SerializedName("description")
	val description: String? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("title")
	val title: String? = null,

	@SerializedName("stock")
	val stock: Int? = null,

	@SerializedName("category")
	val category: String? = null,

	@SerializedName("brand")
	val brand: String? = null
)
