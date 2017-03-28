/**
 * 
 */
package traktTvProject.dao;

/**
 * Holds relevant data from a Trakt User profile JSON string after parsing.
 * Holds a TraktuserProfileStats object which modelled from a sub-JSON object in the profile JSON string
 * The Stats object in turn holds a TraktUserProfileStatsMovie object, which is sub-JSON object which holds stats
 * about how many movies etc a user has watched. 
 *
 */
public class TraktUserProfile {
	
	private String username;
	private String gender;
	private int age;
	private TraktUserProfileStats stats;
	
	/**
	 * Constructor
	 * @param username
	 * @param gender
	 * @param age
	 * @param stats
	 */
	public TraktUserProfile(String username, String gender, int age,
			TraktUserProfileStats stats) {
		super();
		this.username = username;
		this.gender = gender;
		this.age = age;
		this.stats = stats;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TraktUserProfile [username=");
		builder.append(username);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", age=");
		builder.append(age);
		builder.append(", stats=");
		builder.append(stats);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the stats
	 */
	public TraktUserProfileStats getStats() {
		return stats;
	}

	/**
	 * @param stats the stats to set
	 */
	public void setStats(TraktUserProfileStats stats) {
		this.stats = stats;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		if (!(obj instanceof TraktUserProfile)) {
			return false;
		}
		TraktUserProfile other = (TraktUserProfile) obj;
		if (age != other.age) {
			return false;
		}
		if (gender == null) {
			if (other.gender != null) {
				return false;
			}
		} else if (!gender.equals(other.gender)) {
			return false;
		}
		if (stats == null) {
			if (other.stats != null) {
				return false;
			}
		} else if (!stats.equals(other.stats)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}
	
	

}
