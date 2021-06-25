package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest4 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("updown");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// 비영속 상태
			Member member1 = new Member();
			member1.setId(100L);
			member1.setName("HelloJPA");

			Member member2 = new Member();
			member2.setId(200L);
			member2.setName("HelloJPA");

			// 영속 상태 (entityManager)에 올라가있음
			// 1차 캐시에 저장됨
			em.persist(member1);
			em.persist(member2);
			// 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다. 쓰기지연

			// 커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}
