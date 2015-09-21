package net.shinc.orm.mybatis.bean.common;

import java.util.List;


/**
 * 同义词
 * @author zhangtaichao 2015年9月18日
 *
 */
public class SameWord {

	private Integer id;
	private String word;
	private List<String> wordLike;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public List<String> getWordLike() {
		return wordLike;
	}
	public void setWordLike(List<String> wordLike) {
		this.wordLike = wordLike;
	}
	@Override
	public String toString() {
		return "SameWord [id=" + id + ", word=" + word + ", wordLike="
				+ wordLike + "]";
	}
	
	

}
