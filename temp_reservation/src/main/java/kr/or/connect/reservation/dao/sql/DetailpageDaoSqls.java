package kr.or.connect.reservation.dao.sql;

public class DetailpageDaoSqls {
	public static final String SELECT_PRODUCT_BY_ID = "SELECT description, content FROM product WHERE id = :productId";

	public static final String SELECT_PROMOTION_BY_ID = "SELECT description, fi.save_file_name FROM file_info fi, product WHERE product.id = :productId and fi.id IN"
			+ "(SELECT pi.file_id FROM product_image pi "
			+ "WHERE pi.product_id = :productId and pi.type IN(\"ma\",\"et\"))";

	public static final String ETC_IMAGE_COUNT_BY_ID = "SELECT count(*) FROM product_image pi "
			+ "WHERE pi.product_id = :productId and pi.type IN(\"et\")";

	public static final String SELECT_USER_RIVIEW = "SELECT prod.description, RPAD(SUBSTR(ri.reservation_email, 1, 4), 8, '*') AS id, ruc.score, ruc.comment, DATE_FORMAT(ruc.modify_date, '%Y-%m-%d') AS modify_date, IFNULL(CONCAT('./resources/',fi.save_file_name) , '') AS fileURL"
			+ " FROM product prod, display_info display, reservation_info ri, file_info fi RIGHT OUTER JOIN reservation_user_comment_image ruci ON fi.id = ruci.file_id"
			+ " RIGHT OUTER JOIN reservation_user_comment ruc ON ruci.reservation_user_comment_id = ruc.id "
			+ "WHERE prod.id = :productId and display.id = :displayInfoId and display.id = ri.display_info_id and ruc.reservation_info_id = ri.id";

	public static final String USER_REVIEW_AVG_CNT = "SELECT count(*) reviewCnt, COALESCE(round(avg(ruc.score),1),0) AS scoreAvg"
			+ " FROM product prod, display_info display, reservation_info ri, file_info fi RIGHT OUTER JOIN reservation_user_comment_image ruci"
			+ " ON fi.id = ruci.file_id RIGHT OUTER JOIN reservation_user_comment ruc ON ruci.reservation_user_comment_id = ruc.id"
			+ " WHERE prod.id = :productId and display.id = :displayInfoId and display.id = ri.display_info_id and ruc.reservation_info_id = ri.id";

	public static final String SELECT_DISPLAY_INFO = "SELECT prod.description, di.place_name, di.place_lot, di.place_street, di.tel"
			+ " FROM display_info di, product prod WHERE di.id = :displayInfoId and di.product_id = prod.id";
}
