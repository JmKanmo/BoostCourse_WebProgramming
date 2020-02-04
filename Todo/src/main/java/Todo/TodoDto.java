package Todo;

public class TodoDto {
	private Long id;
	private String name;
	private String regDate;
	private int sequence;
	private String title;
	private String type;

	public static class Builder {
		// Optional fields
		private Long id_ = 0L;
		private String type_ = "";
		private String name_ = "";
		private String regDate_ = "";
		private int sequence_ = 0;
		private String title_ = "";

		public Builder id_(Long value) {
			id_ = value;
			return this;
		}

		public Builder type_(String value) {
			type_ = value;
			return this;
		}

		public Builder name_(String value) {
			name_ = value;
			return this;
		}

		public Builder regDate_(String value) {
			regDate_ = value;
			return this;
		}

		public Builder sqeuence_(int value) {
			sequence_ = value;
			return this;
		}

		public Builder title_(String value) {
			title_ = value;
			return this;
		}

		public TodoDto build() {
			return new TodoDto(this);
		}
	}

	private TodoDto(Builder builder) {
		id = builder.id_;
		name = builder.name_;
		regDate = builder.regDate_;
		sequence = builder.sequence_;
		title = builder.title_;
		type = builder.type_;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
