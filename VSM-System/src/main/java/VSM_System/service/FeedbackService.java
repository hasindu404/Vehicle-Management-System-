package VSM_System.service;

import VSM_System.Util.VarList;
import VSM_System.dto.FeedbackDTO;
import VSM_System.entity.Feedback;
import VSM_System.repo.FeedbackRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveFeedback(FeedbackDTO feedbackDTO) {
        if (feedbackRepo.existsById(feedbackDTO.getFeedback_id())) {
            return VarList.RSP_DUPLICATED;
        } else {
            feedbackRepo.save(modelMapper.map(feedbackDTO, Feedback.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateFeedback(FeedbackDTO feedbackDTO) {
        feedbackRepo.save(modelMapper.map(feedbackDTO, Feedback.class));
        return VarList.RSP_SUCCESS;
    }

    public boolean deleteFeedback(FeedbackDTO feedbackDTO) {
        feedbackRepo.delete(modelMapper.map(feedbackDTO, Feedback.class));
        return true;
    }
}
