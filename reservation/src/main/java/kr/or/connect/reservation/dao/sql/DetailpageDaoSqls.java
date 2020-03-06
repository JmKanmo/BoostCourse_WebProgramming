package kr.or.connect.reservation.dao.sql;

public class DetailpageDaoSqls {
	public static final String SELECT_PRODUCT_BY_ID = "SELECT description, content FROM product WHERE id = :id";

	public static final String SELECT_IMAGE_BY_ID = "SELECT fi.save_file_name" + " FROM file_info fi WHERE fi.id IN"
			+ "(SELECT pi.file_id FROM product_image pi "
			+ "WHERE pi.product_id = :productId and pi.type IN(\"ma\",\"et\"))";

	public static final String ETC_IMAGE_COUNT_BY_ID = "SELECT count(*) FROM product_image pi "
			+ "WHERE pi.product_id = :productId and pi.type IN(\"et\")";
}
