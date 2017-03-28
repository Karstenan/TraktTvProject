/**
 * 
 */
package traktTvProject.dao;

/**
 * Not used after adding LinkedMDB queries to the project, but left as an example of easy expansion for further functionality
 *
 */
public class TraktMovieSummary {
	
	
	private String title;
	private int year;
	private String imdb_id;
	/**
	 * @param title
	 * @param year
	 * @param imdb_id
	 */
	public TraktMovieSummary(String title, int year, String imdb_id) {
		super();
		this.title = title;
		this.year = year;
		this.imdb_id = imdb_id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TraktMovieSummary [title=");
		builder.append(title);
		builder.append(", year=");
		builder.append(year);
		builder.append(", imdb_id=");
		builder.append(imdb_id);
		builder.append("]");
		return builder.toString();
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the imdb_id
	 */
	public String getImdb_id() {
		return imdb_id;
	}
	/**
	 * @param imdb_id the imdb_id to set
	 */
	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imdb_id == null) ? 0 : imdb_id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TraktMovieSummary)) {
			return false;
		}
		TraktMovieSummary other = (TraktMovieSummary) obj;
		if (imdb_id == null) {
			if (other.imdb_id != null) {
				return false;
			}
		} else if (!imdb_id.equals(other.imdb_id)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (year != other.year) {
			return false;
		}
		return true;
	}
	
	

}
