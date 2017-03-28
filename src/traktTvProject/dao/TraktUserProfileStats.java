package traktTvProject.dao;

/**
 * 
 * See TraktUserProfile for more information
 *
 */
public class TraktUserProfileStats {
	
	private int friends;
	private TraktUserProfileStatsMovies movies;
	
	/**
	 * Constructor
	 * @param friends
	 * @param movies
	 */
	public TraktUserProfileStats(int friends, TraktUserProfileStatsMovies movies) {
		super();
		this.friends = friends;
		this.movies = movies;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TraktUserProfileStats [friends=");
		builder.append(friends);
		builder.append(", movies=");
		builder.append(movies);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the friends
	 */
	public int getFriends() {
		return friends;
	}

	/**
	 * @param friends the friends to set
	 */
	public void setFriends(int friends) {
		this.friends = friends;
	}

	/**
	 * @return the movies
	 */
	public TraktUserProfileStatsMovies getMovies() {
		return movies;
	}

	/**
	 * @param movies the movies to set
	 */
	public void setMovies(TraktUserProfileStatsMovies movies) {
		this.movies = movies;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + friends;
		result = prime * result + ((movies == null) ? 0 : movies.hashCode());
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
		if (!(obj instanceof TraktUserProfileStats)) {
			return false;
		}
		TraktUserProfileStats other = (TraktUserProfileStats) obj;
		if (friends != other.friends) {
			return false;
		}
		if (movies == null) {
			if (other.movies != null) {
				return false;
			}
		} else if (!movies.equals(other.movies)) {
			return false;
		}
		return true;
	}
	
	
	

}
