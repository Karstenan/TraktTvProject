package traktTvProject.dao;


/**
 * 
 * UserMoviesWatched is a helper object that holds the minimum amount of information to identify
 * one movie from a JSON array that contains a list of all movies watched by a single user.
 * These are put into a linked list for easy handling as they come from a list, this is natural way of handling it.
 *
 */
public class UserMoviesWatched {

	private String imdb_id;
	private String title;
	private int plays;
	/**
	 * @param imdb_id
	 * @param title
	 * @param plays
	 */
	public UserMoviesWatched(String imdb_id, String title, int plays) {
		super();
		this.imdb_id = imdb_id;
		this.title = title;
		this.plays = plays;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserMoviesWatched [imdb_id=");
		builder.append(imdb_id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", plays=");
		builder.append(plays);
		builder.append("]\n");
		return builder.toString();
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
	 * @return the plays
	 */
	public int getPlays() {
		return plays;
	}
	/**
	 * @param plays the plays to set
	 */
	public void setPlays(int plays) {
		this.plays = plays;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imdb_id == null) ? 0 : imdb_id.hashCode());
		result = prime * result + plays;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (!(obj instanceof UserMoviesWatched)) {
			return false;
		}
		UserMoviesWatched other = (UserMoviesWatched) obj;
		if (imdb_id == null) {
			if (other.imdb_id != null) {
				return false;
			}
		} else if (!imdb_id.equals(other.imdb_id)) {
			return false;
		}
		if (plays != other.plays) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}
	
	
	
}
