//
//package com.lt.vbank.service;
//
//import com.lt.vbank.model.AccountType;
//import com.lt.vbank.repository.AccountRepository;
//import com.lt.vbank.repository.AccountTypeRepository;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnit;
//import org.mockito.junit.MockitoRule;
//
//import static org.mockito.Mockito.when;
//
//
////@RunWith(BlockJUnit4ClassRunner.class)
////@RunWith(MockitoJUnitRunner.class)
//public class AccountServiceTest {
//
//    @Rule
//    public MockitoRule rule = MockitoJUnit.rule();
//
////    @Before
////    public void initMocks() {
////        MockitoAnnotations.initMocks(this);
////    }
////
//    @Before
//    public void setUp() throws Exception {
////        service.createAccount("IVANOV","DEBIT");
////        service.createAccount("PETROV","CREDIT");
////        service.createAccount("SIDOROV","DEBIT");
//    }
//
////    @InjectMocks
////    private AccountTypeRepository typeRepo = new ;
//
//    @Mock
//    private AccountRepository repo;
//
//    @InjectMocks
//    private AccountService service = new AccountServiceImpl();
//
//    @Test
//    public void findAll_ShouldReturnTwo() throws Exception {
//        when(typeRepo.findByName("CREDIT")).thenReturn(new AccountType("CREDIT"));
//        service.createAccount("PETROV","CREDIT");
//
//
////        service.printAccounts();
//////        List<Account>accounts = Arrays.asLis//                new Account(),
//////                new Account()
////        service.createAccount("IVANOV","DEBIT");
////        service.createAccount("PETROV","CREDIT");
////        service.printAccounts();
//
////        List<Account> accounts = (List<Account>) service.getAccounts();
////        );
//
////        when(repo.findAll()).thenReturn(accounts);
////
////        assertEquals("findAll should return two favorites",2,
////                service.getAccounts().spliterator().getExactSizeIfKnown());
//    }
//
//}
//
