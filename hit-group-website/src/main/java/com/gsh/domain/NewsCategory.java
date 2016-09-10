package com.gsh.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_news_category")
public class NewsCategory implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_category_id")
	private Long newsCategoryId;

	@Column(name = "category", unique = true, nullable = false)
	private String category;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "newsCategory")
	private Set<News> newses = new HashSet<>();

	public NewsCategory()
	{
	}

	public Long getNewsCategoryId()
	{
		return newsCategoryId;
	}

	public void setNewsCategoryId(Long newsCategoryId)
	{
		this.newsCategoryId = newsCategoryId;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public Set<News> getNewses()
	{
		return newses;
	}

	public void setNewses(Set<News> newses)
	{
		this.newses = newses;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NewsCategory that = (NewsCategory) o;

		if (newsCategoryId != null ? !newsCategoryId.equals(that.newsCategoryId) : that.newsCategoryId != null)
			return false;
		return !(category != null ? !category.equals(that.category) : that.category != null);

	}

	@Override
	public int hashCode()
	{
		int result = newsCategoryId != null ? newsCategoryId.hashCode() : 0;
		result = 31 * result + (category != null ? category.hashCode() : 0);
		return result;
	}
}
