package kr.or.connect.reservation.dao;

public class ReservationDaoSqls {
	public static final String SELECT_PROMOTIONS = "SELECT fi.save_file_name FROM file_info fi, promotion pr WHERE fi.id "
			+ "in (SELECT pi.file_id FROM product_image pi WHERE pi.product_id = pr.product_id and pi.type = \"th\") order by rand()";

	public static final String SELECT_CATEGORY = "SELECT category.id, category.name FROM category";

	public static final String SELECT_ALL_PRODUCTS = "SELECT product.description, display.place_name, product.content, fi.save_file_name"
			+ " FROM product, display_info display, file_info fi, product_image pi"
			+ " WHERE product.id = display.product_id and pi.product_id = product.id and pi.file_id = fi.id "
			+ "group by product.content order by rand() limit :turn,:cnt";

	public static final String SELECT_PRODUCTS_BY_CATEGORY = "SELECT product.description, display.place_name, product.content, fi.save_file_name"
			+ " FROM product , display_info display, file_info fi, product_image pi"
			+ " WHERE product.id = display.product_id and pi.product_id = product.id"
			+ " and pi.file_id = fi.id and product.category_id = :id"
			+ " group by product.content order by product.id limit :turn,:cnt";

	public static final String SELECT_ALL_PRODUCT_COUNT = "SELECT count(*) FROM product";

	public static final String SELECT_PRODUCT_COUNT_BY_CATEGORY = "SELECT count(*) FROM category, product"
			+ " WHERE category.id = :id and category.id = product.category_id group by product.category_id";

}
