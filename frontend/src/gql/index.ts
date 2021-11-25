/* eslint-disable */
import * as graphql from "./graphql";
import { TypedDocumentNode as DocumentNode } from "@graphql-typed-document-node/core";

const documents = {
  "\n  query owner($id: Long) {\n    owner(id: $id) {\n      address\n      city\n      email\n      firstName\n      id\n      lastName\n      telephone\n    }\n  }\n":
    graphql.OwnerDocument,
  "\n  mutation update_Owner($input: OwnerInputDTOInput) {\n    update_Owner(input: $input) {\n      address\n      city\n      email\n      firstName\n      id\n      lastName\n      telephone\n    }\n  }\n":
    graphql.Update_OwnerDocument,
  "\n              fragment New_OwnerDTO on OwnerDTO {\n                id\n              }\n            ":
    graphql.New_OwnerDtoFragmentDoc,
  "\n  query ownerList {\n    ownerList {\n      address\n      city\n      email\n      firstName\n      id\n      lastName\n      telephone\n    }\n  }\n":
    graphql.OwnerListDocument,
  "\n  mutation delete_Owner($id: Long!) {\n    delete_Owner(id: $id)\n  }\n":
    graphql.Delete_OwnerDocument,
  "\n  query pet($id: Long) {\n    pet(id: $id) {\n      birthDate\n      id\n      identificationNumber\n      owner {\n        address\n        city\n        email\n        firstName\n        id\n        lastName\n        telephone\n      }\n      type {\n        id\n        name\n      }\n    }\n  }\n":
    graphql.PetDocument,
  "\n  mutation update_Pet($input: PetInputDTOInput) {\n    update_Pet(input: $input) {\n      birthDate\n      id\n      identificationNumber\n    }\n  }\n":
    graphql.Update_PetDocument,
  "\n              fragment New_PetDTO on PetDTO {\n                id\n              }\n            ":
    graphql.New_PetDtoFragmentDoc,
  "\n  query petList($page: PaginationInput) {\n    petList(page: $page) {\n      birthDate\n      id\n      identificationNumber\n      owner {\n        firstName\n        lastName\n      }\n    }\n  }\n":
    graphql.PetListDocument,
  "\n  mutation delete_Pet($id: Long!) {\n    delete_Pet(id: $id)\n  }\n":
    graphql.Delete_PetDocument,
};

export function gql(
  source: "\n  query owner($id: Long) {\n    owner(id: $id) {\n      address\n      city\n      email\n      firstName\n      id\n      lastName\n      telephone\n    }\n  }\n"
): typeof documents["\n  query owner($id: Long) {\n    owner(id: $id) {\n      address\n      city\n      email\n      firstName\n      id\n      lastName\n      telephone\n    }\n  }\n"];
export function gql(
  source: "\n  mutation update_Owner($input: OwnerInputDTOInput) {\n    update_Owner(input: $input) {\n      address\n      city\n      email\n      firstName\n      id\n      lastName\n      telephone\n    }\n  }\n"
): typeof documents["\n  mutation update_Owner($input: OwnerInputDTOInput) {\n    update_Owner(input: $input) {\n      address\n      city\n      email\n      firstName\n      id\n      lastName\n      telephone\n    }\n  }\n"];
export function gql(
  source: "\n              fragment New_OwnerDTO on OwnerDTO {\n                id\n              }\n            "
): typeof documents["\n              fragment New_OwnerDTO on OwnerDTO {\n                id\n              }\n            "];
export function gql(
  source: "\n  query ownerList {\n    ownerList {\n      address\n      city\n      email\n      firstName\n      id\n      lastName\n      telephone\n    }\n  }\n"
): typeof documents["\n  query ownerList {\n    ownerList {\n      address\n      city\n      email\n      firstName\n      id\n      lastName\n      telephone\n    }\n  }\n"];
export function gql(
  source: "\n  mutation delete_Owner($id: Long!) {\n    delete_Owner(id: $id)\n  }\n"
): typeof documents["\n  mutation delete_Owner($id: Long!) {\n    delete_Owner(id: $id)\n  }\n"];
export function gql(
  source: "\n  query pet($id: Long) {\n    pet(id: $id) {\n      birthDate\n      id\n      identificationNumber\n      owner {\n        address\n        city\n        email\n        firstName\n        id\n        lastName\n        telephone\n      }\n      type {\n        id\n        name\n      }\n    }\n  }\n"
): typeof documents["\n  query pet($id: Long) {\n    pet(id: $id) {\n      birthDate\n      id\n      identificationNumber\n      owner {\n        address\n        city\n        email\n        firstName\n        id\n        lastName\n        telephone\n      }\n      type {\n        id\n        name\n      }\n    }\n  }\n"];
export function gql(
  source: "\n  mutation update_Pet($input: PetInputDTOInput) {\n    update_Pet(input: $input) {\n      birthDate\n      id\n      identificationNumber\n    }\n  }\n"
): typeof documents["\n  mutation update_Pet($input: PetInputDTOInput) {\n    update_Pet(input: $input) {\n      birthDate\n      id\n      identificationNumber\n    }\n  }\n"];
export function gql(
  source: "\n              fragment New_PetDTO on PetDTO {\n                id\n              }\n            "
): typeof documents["\n              fragment New_PetDTO on PetDTO {\n                id\n              }\n            "];
export function gql(
  source: "\n  query petList($page: PaginationInput) {\n    petList(page: $page) {\n      birthDate\n      id\n      identificationNumber\n      owner {\n        firstName\n        lastName\n      }\n    }\n  }\n"
): typeof documents["\n  query petList($page: PaginationInput) {\n    petList(page: $page) {\n      birthDate\n      id\n      identificationNumber\n      owner {\n        firstName\n        lastName\n      }\n    }\n  }\n"];
export function gql(
  source: "\n  mutation delete_Pet($id: Long!) {\n    delete_Pet(id: $id)\n  }\n"
): typeof documents["\n  mutation delete_Pet($id: Long!) {\n    delete_Pet(id: $id)\n  }\n"];

export function gql(source: string): unknown;
export function gql(source: string) {
  return (documents as any)[source] ?? {};
}

export type DocumentType<TDocumentNode extends DocumentNode<any, any>> =
  TDocumentNode extends DocumentNode<infer TType, any> ? TType : never;
