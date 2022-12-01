package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class GroupRepositoryImpl implements GroupRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Group> getAllGroup(Long id) {
//        return entityManager.createQuery("select g from Group g where g.course.id = :id", Group.class).setParameter("id", id).getResultList();

        List<Group> groupList = entityManager.find(Course.class,id).getGroups();
        groupList.forEach(System.out::println);
        return groupList;
    }
//    @Override
//    public List<Group> getAllGroupsByCourseId(Long courseId) {
//        List<Group> groupList = entityManager.find(Course.class, courseId).getGroups();
//        groupList.forEach(System.out::println);
//        return groupList;
//    }

    @Override
    public void addGroup(Long id, Group group){
        Course course = entityManager.find(Course.class,id);


        course.addGroup(group);
        group.addCourse(course);
        entityManager.merge(group);
    }


    @Override
    public Group getGroupById(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public void updateGroup(Group group, Long id) {
        Group group1 = getGroupById(id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setImage(group.getImage());
        entityManager.merge(group1);
    }

    @Override
    public void deleteGroup(Long id) {
        Group group = entityManager.find(Group.class, id);
    entityManager.remove(group);
    }

    @Override
    public void assignGroup(Long courseId, Long groupId) {
        Group group = entityManager.find(Group.class, groupId);
        Course course = entityManager.find(Course.class, courseId);
        group.addCourse(course);
        course.addGroup(group);
        entityManager.merge(group);
        entityManager.merge(course);
    }
}
