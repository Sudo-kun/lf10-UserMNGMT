package de.oszimt.lf10ContractMgmt.mock_data;

import de.oszimt.lf10ContractMgmt.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MockDataCreator {
    public Contract[] createContractMockData(int wantedLength) {
        Contract[] contracts = new Contract[wantedLength];

        Customer[] customers = new Customer[wantedLength];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer("firstname" + i, "lastname" + i, LocalDate.now().minusYears((int) (Math.random() * 50)), "email" + i + "@mail.com", new Address("street" + i, "" + (int) (Math.random() * 100), "" + (int) (Math.random() * 100000), "city" + i, "Germany"));
        }

        Employee[] employees = new Employee[wantedLength];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee(
                    "firstname" + i,
                    "lastname" + i,
                    new Address("street" + i,
                            "" + (int) (Math.random() * 100),
                            "" + (int) (Math.random() * 100000),
                            "city" + i, "Germany"),
                    "email" + i + "@mail.com",
                    "" + (int) (Math.random() * 100000000));
        }

        ActivityRecord[][] activityRecords = new ActivityRecord[wantedLength][wantedLength];
        for (int i = 0; i < activityRecords.length; i++) {
            for (int j = 0; j < activityRecords[i].length; j++) {
                activityRecords[i][j] = new ActivityRecord(
                        LocalDate.now()
                                .minusYears(1)
                                .plusDays((int) (Math.random() * 365)),
                        LocalTime.now()
                                .withHour((int) (Math.random() * 24))
                                .withMinute((int) (Math.random() * 12)),
                        LocalTime.now()
                                .withHour((int) (Math.random() * 24))
                                .withMinute((int) (Math.random() * 12)),
                        employees[(int) (Math.random() * wantedLength)],
                        "description" + i + j);
            }
        }

        for (int i = 0; i < contracts.length; i++) {
            contracts[i] = new Contract(
                    LocalDate.now()
                            .minusYears(1)
                            .plusDays((int) (Math.random() * 365)),
                    new Address("street" + i, "" + (int) (Math.random() * 100), "" + (int) (Math.random() * 100000), "city" + i, "Germany"), customers[(int) (Math.random() * wantedLength)], employees[(int) (Math.random() * wantedLength)], "Service", "In Progress", "description" + i, new ArrayList<>(List.of(activityRecords[i])));
        }

        return contracts;
    }
}
