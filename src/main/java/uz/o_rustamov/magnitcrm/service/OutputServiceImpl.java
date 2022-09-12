package uz.o_rustamov.magnitcrm.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.abs_interface.OutputService;
import uz.o_rustamov.magnitcrm.entity.*;
import uz.o_rustamov.magnitcrm.error_handling.ReverseException;
import uz.o_rustamov.magnitcrm.fcm.FirebaseMessagingService;
import uz.o_rustamov.magnitcrm.payload.InputOutputProductDto;
import uz.o_rustamov.magnitcrm.payload.OutputDto;
import uz.o_rustamov.magnitcrm.repository.OutputProductRepository;
import uz.o_rustamov.magnitcrm.repository.OutputRepository;
import uz.o_rustamov.magnitcrm.repository.ProductRepository;
import uz.o_rustamov.magnitcrm.repository.RecipientRepository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static uz.o_rustamov.magnitcrm.Constants.*;

@Service
public class OutputServiceImpl implements OutputService {

    FirebaseMessagingService fcm;
    RecipientRepository recipientRepository;

    ProductRepository productRepository;
    OutputRepository outputRepository;
    OutputProductRepository outputProductRepository;

    public OutputServiceImpl(OutputRepository outputRepository, OutputProductRepository outputProductRepository,
                             RecipientRepository recipientRepository, ProductRepository productRepository,
                             FirebaseMessagingService fcm) {
        this.outputRepository = outputRepository;
        this.outputProductRepository = outputProductRepository;
        this.recipientRepository = recipientRepository;
        this.productRepository = productRepository;
        this.fcm = fcm;
    }


    @Override
    public HttpEntity<ApiResponse> getAllOutputs() {
        Map<String, Object> data = new HashMap<String, Object>();

        long sumTakenMoney = 0L;
        long sumAllProductCost = 0L;
        long countOutputs = 0L;
        try {
            sumTakenMoney = outputRepository.sumTakenMoney();
            sumAllProductCost = outputRepository.sumAllProductsCost();
            countOutputs = outputRepository.count();
        } catch (NullPointerException ignored) {
        }
        data.put("outputs", outputRepository.findAllDesc());
        data.put("taken_money", sumTakenMoney);
        data.put("all_product_cost", sumAllProductCost);
        data.put("difference", sumAllProductCost - sumTakenMoney);
        data.put("count", countOutputs);
        return ResponseEntity.ok(new ApiResponse(null, 200, true, data));
    }

    @Override
    public HttpEntity<ApiResponse> getOutputById(Long id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()) {
            Map<String, Object> data = new HashMap<>();
            data.put("output", optionalOutput.get());
            data.put("product_list", outputProductRepository.findAllByOutput_Id(id));
            return ResponseEntity.ok(new ApiResponse(null, 200, true, data));
        }
        return NOT_FOUND;
    }

    @Override
    public HttpEntity<ApiResponse> getOutputByDate(String fromDate, String toDate) {
        try {
            Date from = new Date(new SimpleDateFormat("dd.MM.yyyy").parse(fromDate).getTime());
            Date to = new Date(new SimpleDateFormat("dd.MM.yyyy").parse(toDate).getTime());
            Map<String, Object> data = new HashMap<String, Object>();
            long sumTakenMoney = 0L;
            long sumAllProductCost = 0L;
            long countOutputs = 0L;
            try {
                sumTakenMoney = outputRepository.sumTakenMoney(from, to);
                sumAllProductCost = outputRepository.sumAllProductsCost(from, to);
                countOutputs = outputRepository.countByPeriod(from, to);
            } catch (NullPointerException ignored) {
            }

            data.put("outputs", outputRepository.findAllByPeriod(from, to));
            data.put("taken_money", sumTakenMoney);
            data.put("all_product_cost", sumAllProductCost);
            data.put("difference", sumAllProductCost - sumTakenMoney);
            data.put("count", countOutputs);
            return ResponseEntity.ok(new ApiResponse(null, 200, true, data));
        } catch (ParseException e) {
            return PARSE_EXCEPTION;
        }
    }

    @Override
    public HttpEntity<ApiResponse> getOutputByDateAndRecipientId(String fromDate, String toDate, Long recipientId) {
        Optional<Recipient> optionalRecipient = recipientRepository.findById(recipientId);
        if (!optionalRecipient.isPresent()) return NOT_FOUND;
        Recipient recipient = optionalRecipient.get();
        try {
            Date from = new Date(new SimpleDateFormat("dd.MM.yyyy").parse(fromDate).getTime());
            Date to = new Date(new SimpleDateFormat("dd.MM.yyyy").parse(toDate).getTime());
            Map<String, Object> data = new HashMap<String, Object>();
            long sumTakenMoney = 0L;
            long sumAllProductCost = 0L;
            long countOutputs = 0L;
            try {
                sumTakenMoney = outputRepository.sumTakenMoney(from, to, recipientId);
                sumAllProductCost = outputRepository.sumAllProductsCost(from, to, recipientId);
                countOutputs = outputRepository.countByPeriodAndRecipient(from, to, recipientId);
            } catch (NullPointerException ignored) {
            }

            data.put("outputs", outputRepository.findAllByPeriodAndRecipientId(from, to, recipientId));
            data.put("taken_money", sumTakenMoney);
            data.put("all_product_cost", sumAllProductCost);
            data.put("difference", sumAllProductCost - sumTakenMoney);
            data.put("count", countOutputs);
            return ResponseEntity.ok(new ApiResponse(null, 200, true, data));
        } catch (ParseException e) {
            return PARSE_EXCEPTION;
        }
    }

    @Override
    public HttpEntity<ApiResponse> getAllByRecipientId(Long recipientId) {
        Optional<Recipient> optionalRecipient = recipientRepository.findById(recipientId);
        if (!optionalRecipient.isPresent()) return NOT_FOUND;
        Map<String, Object> data = new HashMap<String, Object>();
        long sumTakenMoney = 0L;
        long sumAllProductCost = 0L;
        long countOutputs = 0L;
        try {
            sumTakenMoney = outputRepository.sumTakenMoney(recipientId);
            sumAllProductCost = outputRepository.sumAllProductsCost(recipientId);
            countOutputs = outputRepository.countByRecipient_Id(recipientId);
        } catch (NullPointerException ignored) {
        }

        data.put("outputs", outputRepository.findAllByRecipient_Id(recipientId));
        data.put("taken_money", sumTakenMoney);
        data.put("all_product_cost", sumAllProductCost);
        data.put("difference", sumAllProductCost - sumTakenMoney);
        data.put("count", countOutputs);
        return ResponseEntity.ok(new ApiResponse(null, 200, true, data));
    }

    @Override
    public HttpEntity<ApiResponse> addOutput(User user, OutputDto dto) {
        Optional<Recipient> optionalRecipient = recipientRepository.findById(dto.getRecipientId());
        if (!optionalRecipient.isPresent()) return NOT_FOUND;
        Date date;
        try {
            date = new Date(new SimpleDateFormat("dd.MM.yyyy").parse(dto.getDate()).getTime());
        } catch (ParseException e) {
            return PARSE_EXCEPTION;
        }
        Recipient recipient = optionalRecipient.get();
        Long costAllProducts = 0L;
        Output output = new Output(date, costAllProducts, dto.getTakenMoney(), user, recipient, dto.getNote(), false);
        if (recipient.getUser() == null) {
            output.setCheckedByRecipient(true);
        }
        output = outputRepository.save(output);
        List<InputOutputProductDto> products = dto.getProductList();
        for (InputOutputProductDto inputOutputProductDto : products) {
            Optional<Product> optionalProduct = productRepository.findById(inputOutputProductDto.getProductId());
            if (!optionalProduct.isPresent()) throw new ReverseException("Mahsulot mavjud emas");
            Product product = optionalProduct.get();
            if (product.getBalance() < inputOutputProductDto.getBoxCount())
                throw new ReverseException("Mahsulot yetarli emas. Kichikroq miqdor belgilang");
            OutputProduct outputProduct = new OutputProduct(
                    product, inputOutputProductDto.getBoxCount(), output, product.getCostForDriver());
            outputProduct = outputProductRepository.save(outputProduct);
            if (recipient.getUser() == null) {
                costAllProducts += (long) inputOutputProductDto.getBoxCount() *
                        product.getCostForClient() * product.getCountInBox();
            } else if (recipient.getUser().getRole().getName().equals(ROLE_DRIVER)) {
                costAllProducts += (long) inputOutputProductDto.getBoxCount() *
                        product.getCostForDriver() * product.getCountInBox();
            }
            product.setBalance(product.getBalance() - outputProduct.getBoxCount());
            productRepository.save(product);
        }
        output.setAllProductCost(costAllProducts);
        output = outputRepository.save(output);
        try {
            fcm.sendNotiToOtherManagers("Yangi zapravka", recipient.getName() + " ga mahsulotlar topshirildi. Manager:" + user.getFullName(), user.getFcmToken());
            if (recipient.getUser() != null && recipient.getUser().getFcmToken() != null)
                fcm.sendNotification("Yangi zapravka", "Sizning nomingizga yangi zapravka yozildi. Manager:" + user.getFullName(), recipient.getUser().getFcmToken());
        } catch (FirebaseMessagingException e) {
            return FCM_ERROR;
        }
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> deleteOutput(Long id) {
        try {
            outputProductRepository.deleteAllByOutput_Id(id);
            outputRepository.deleteById(id);
            return SUCCESS;
        } catch (Exception e) {
            return CONNECTED_WITH_OTHERS_EXCEPTION;
        }
    }

    @Override
    public HttpEntity<ApiResponse> confirmOutput(User user, Long outputId) {
        try {
            Optional<Output> optionalOutput = outputRepository.findById(outputId);
            if (!optionalOutput.isPresent()) return NOT_FOUND;
            Output output = optionalOutput.get();
            if (output.getRecipient().getUser().getId().equals(user.getId())) {
                outputRepository.confirmOutput(outputId);
                return SUCCESS;
            } else {
                return YOU_DONT_HAVE_ACCESS;
            }
        } catch (NullPointerException ex) {
            return YOU_DONT_HAVE_ACCESS;
        }
    }
}
