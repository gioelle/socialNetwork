package com.claim.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.entity.Person;
import com.claim.entity.Post;
import com.claim.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	//find all my posts
	@Transactional
	public List<Post> findMyPost(String email){
		String[] emails = {email};
		return this.postRepository.findPost(emails);
	}
	
	//save my post
	@Transactional
	public void savePost(Post post) {
		this.postRepository.save(post);
	}

	//If we had a friends list	
//	@Transactional
//	public List<Post> findAllPosts(Person person){
//		String[] emails = person.getFriendsList();
//		//return this.postRepository.findPost(emails);
//	}
	
}
