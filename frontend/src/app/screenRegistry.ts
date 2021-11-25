import { PetList } from "./pets/PetList";
import { PetTypeList } from "./pettypes/PetTypeList";
import { OwnerList } from "./owners/OwnerList";
import { Home } from "./home/Home";
import { screenStore } from "@amplicode/react-core";

screenStore.registerScreen("home", {
  component: Home,
  captionKey: "screen.home"
});

screenStore.registerScreen("owner-list", {
  component: OwnerList,
  captionKey: "screen.OwnerList"
});

screenStore.registerScreen("pet-type-list", {
  component: PetTypeList,
  captionKey: "screen.PetTypeList"
});

screenStore.registerScreen("pet-list", {
  component: PetList,
  captionKey: "screen.PetList"
});
