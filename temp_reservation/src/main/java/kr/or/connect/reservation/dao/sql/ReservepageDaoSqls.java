package kr.or.connect.reservation.dao.sql;

public class ReservepageDaoSqls {
	public static final String SELECT_DISPLAY_INFO = "SELECT if(DATEDIFF(TRIM(SUBSTRING_INDEX(di.opening_term,'~', -1)), now())>=4,date_add(curdate(),INTERVAL CAST(RAND() * 4 AS SIGNED) + 0 DAY) "
			+ ",date_add(curdate(),INTERVAL CAST(RAND() * DATEDIFF(TRIM(SUBSTRING_INDEX(di.opening_term,'~', -1)), now()) AS SIGNED) + 0 DAY)) resrv_date,"
			+ " prod.description, di.place_name, di.place_lot, di.place_street, di.tel, di.opening_hours, di.opening_term, CONCAT('./resources/',fi.save_file_name) save_file_name"
			+ " FROM display_info di, product prod, file_info fi WHERE di.id = :displayInfoId and di.product_id = prod.id"
			+ " and fi.id in (select pi.file_id from product_image pi where product_id = :productId and pi.type = \"ma\")";

	public static final String SELECT_PRODUCT_PRICE_INFO = "SELECT id, price_type_name, price, discount_rate,(price-(price*(discount_rate/100))) discounted_price FROM product_price WHERE product_id= :productId";
}