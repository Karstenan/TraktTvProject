package traktTvProject.dao;

/**
 * 
 * See TraktUserProfile for more information.
 *
 */
public class TraktUserProfileStatsMovies {

	private int watched;
	private int watched_unique;
	
	/**
	 * Constructor
	 * @param watched
	 * @param watched_unique
	 */
	public TraktUserProfileStatsMovies(int watched, int watched_unique) {
		super();
		this.watched = watched;
		this.watched_unique = watched_unique;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TraktUserProfileStatsMovies [watched=");
		builder.append(watched);
		builder.append(", watched_unique=");
		builder.append(watched_unique);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the watched
	 */
	public int getWatched() {
		return watched;
	}

	/**
	 * @param watched the watched to set
	 */
	public void setWatched(int watched) {
		this.watched = watched;
	}

	/**
	 * @return the watched_unique
	 */
	public int getWatched_unique() {
		return watched_unique;
	}

	/**
	 * @param watched_unique the watched_unique to set
	 */
	public void setWatched_unique(int watched_unique) {
		this.watched_unique = watched_unique;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + watched;
		result = prime * result + watched_unique;
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
		if (!(obj instanceof TraktUserProfileStatsMovies)) {
			return false;
		}
		TraktUserProfileStatsMovies other = (TraktUserProfileStatsMovies) obj;
		if (watched != other.watched) {
			return false;
		}
		if (watched_unique != other.watched_unique) {
			return false;
		}
		return true;
	}
	
	
	
	
}
