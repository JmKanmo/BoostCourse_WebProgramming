package kr.or.connect.reservation.dao.sql;

public class DetailpageDaoSqls {
	public static final String SELECT_PRODUCT_BY_ID = "SELECT description, content FROM product WHERE id = :id";

	public static final String SELECT_IMAGE_BY_ID = "SELECT fi.save_file_name FROM file_info fi WHERE fi.id IN"
			+ "(SELECT pi.file_id FROM product_image pi "
			+ "WHERE pi.product_id = :productId and pi.type IN(\"ma\",\"et\"))";

	public static final String ETC_IMAGE_COUNT_BY_ID = "SELECT count(*) FROM product_image pi "
			+ "WHERE pi.product_id = :productId and pi.type IN(\"et\")";

	public static final String SELECT_USER_RIVIEW = "SELECT prod.description, RPAD(SUBSTR(ri.reservation_email, 1, 4), 8, '*') AS id, ruc.score, ruc.comment, ruc.modify_date, COALESCE(fi.save_file_name,'') AS fileURL"
			+ " FROM product prod, reservation_info ri, file_info fi RIGHT OUTER JOIN reservation_user_comment_image ruci ON fi.id = ruci.file_id"
			+ " RIGHT OUTER JOIN reservation_user_comment ruc ON ruci.reservation_user_comment_id = ruc.id "
			+ "WHERE prod.id = :productId and prod.id = ri.product_id and ruc.reservation_info_id = ri.id";

	public static final String USER_REVIEW_AVG_CNT = "SELECT count(*) reviewCnt, COALESCE(round(avg(ruc.score),1),0) AS scoreAvg"
			+ " FROM product prod, reservation_info ri, file_info fi RIGHT OUTER JOIN reservation_user_comment_image ruci"
			+ " ON fi.id = ruci.file_id RIGHT OUTER JOIN reservation_user_comment ruc ON ruci.reservation_user_comment_id = ruc.id"
			+ " WHERE prod.id = :productId and prod.id = ri.product_id and ruc.reservation_info_id = ri.id";
}
