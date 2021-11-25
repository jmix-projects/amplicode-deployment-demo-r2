/* eslint-disable */
import { TypedDocumentNode as DocumentNode } from "@graphql-typed-document-node/core";
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = {
  [K in keyof T]: T[K];
};
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & {
  [SubKey in K]?: Maybe<T[SubKey]>;
};
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & {
  [SubKey in K]: Maybe<T[SubKey]>;
};
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
  /** BigDecimal */
  BigDecimal: any;
  /** BigInteger */
  BigInteger: any;
  /** Byte */
  Byte: any;
  /** Char */
  Char: any;
  /** Date */
  Date: any;
  /** DateTime */
  DateTime: any;
  /** LocalDate */
  LocalDate: any;
  /** LocalDateTime */
  LocalDateTime: any;
  /** LocalTime */
  LocalTime: any;
  /** Long */
  Long: any;
  /** OffsetDateTime */
  OffsetDateTime: any;
  /** OffsetTime */
  OffsetTime: any;
  /** Short */
  Short: any;
  /** Time */
  Time: any;
  /** UUID */
  UUID: any;
  /** Void */
  Void: any;
  /** currency */
  currency: any;
};

export type BaseLocale = {
  __typename?: "BaseLocale";
  hash: Scalars["Int"];
  language?: Maybe<Scalars["String"]>;
  region?: Maybe<Scalars["String"]>;
  script?: Maybe<Scalars["String"]>;
  variant?: Maybe<Scalars["String"]>;
};

export type BaseLocaleInput = {
  hash: Scalars["Int"];
  language?: InputMaybe<Scalars["String"]>;
  region?: InputMaybe<Scalars["String"]>;
  script?: InputMaybe<Scalars["String"]>;
  variant?: InputMaybe<Scalars["String"]>;
};

export type ByteInput = {
  value: Scalars["Byte"];
};

export type Calendar = {
  __typename?: "Calendar";
  areAllFieldsSet: Scalars["Boolean"];
  areFieldsSet: Scalars["Boolean"];
  firstDayOfWeek: Scalars["Int"];
  isTimeSet: Scalars["Boolean"];
  lenient: Scalars["Boolean"];
  minimalDaysInFirstWeek: Scalars["Int"];
  nextStamp: Scalars["Int"];
  serialVersionOnStream: Scalars["Int"];
  sharedZone: Scalars["Boolean"];
  zone?: Maybe<TimeZone>;
};

export type CalendarInput = {
  areAllFieldsSet: Scalars["Boolean"];
  areFieldsSet: Scalars["Boolean"];
  firstDayOfWeek: Scalars["Int"];
  isTimeSet: Scalars["Boolean"];
  lenient: Scalars["Boolean"];
  minimalDaysInFirstWeek: Scalars["Int"];
  nextStamp: Scalars["Int"];
  serialVersionOnStream: Scalars["Int"];
  sharedZone: Scalars["Boolean"];
  zone?: InputMaybe<TimeZoneInput>;
};

export type Character = {
  __typename?: "Character";
  value: Scalars["Char"];
};

export type CharacterInput = {
  value: Scalars["Char"];
};

export type DateInput = {};

export enum Direction {
  Asc = "ASC",
  Desc = "DESC",
}

export type Duration = {
  __typename?: "Duration";
  nanos: Scalars["Int"];
};

export type DurationInput = {
  nanos: Scalars["Int"];
};

export type InetAddress = {
  __typename?: "InetAddress";
  canonicalHostName?: Maybe<Scalars["String"]>;
  holder?: Maybe<InetAddressHolder>;
};

export type InetAddressHolder = {
  __typename?: "InetAddressHolder";
  address: Scalars["Int"];
  family: Scalars["Int"];
  hostName?: Maybe<Scalars["String"]>;
  originalHostName?: Maybe<Scalars["String"]>;
};

export type InetAddressHolderInput = {
  address: Scalars["Int"];
  family: Scalars["Int"];
  hostName?: InputMaybe<Scalars["String"]>;
  originalHostName?: InputMaybe<Scalars["String"]>;
};

export type InetAddressInput = {
  canonicalHostName?: InputMaybe<Scalars["String"]>;
  holder?: InputMaybe<InetAddressHolderInput>;
};

export type Instant = {
  __typename?: "Instant";
  nanos: Scalars["Int"];
};

export type InstantInput = {
  nanos: Scalars["Int"];
};

export type Locale = {
  __typename?: "Locale";
  baseLocale?: Maybe<BaseLocale>;
  hashCodeValue: Scalars["Int"];
  languageTag?: Maybe<Scalars["String"]>;
  localeExtensions?: Maybe<LocaleExtensions>;
};

export type LocaleExtensions = {
  __typename?: "LocaleExtensions";
  extensionMap?: Maybe<Map>;
  id?: Maybe<Scalars["String"]>;
};

export type LocaleExtensionsInput = {
  extensionMap?: InputMaybe<MapInput>;
  id?: InputMaybe<Scalars["String"]>;
};

export type LocaleInput = {
  baseLocale?: InputMaybe<BaseLocaleInput>;
  hashCodeValue: Scalars["Int"];
  languageTag?: InputMaybe<Scalars["String"]>;
  localeExtensions?: InputMaybe<LocaleExtensionsInput>;
};

export type Map = {
  __typename?: "Map";
};

export type MapInput = {};

/** Mutation root */
export type Mutation = {
  __typename?: "Mutation";
  delete_Owner: Scalars["Boolean"];
  delete_Pet: Scalars["Boolean"];
  delete_PetType: Scalars["Boolean"];
  delete_Test: Scalars["Boolean"];
  delete_Visit: Scalars["Boolean"];
  update_Owner?: Maybe<OwnerDto>;
  update_Pet?: Maybe<PetDto>;
  update_PetType?: Maybe<PetTypeDto>;
  update_Test?: Maybe<TestDto>;
  update_Visit?: Maybe<VisitDto>;
};

/** Mutation root */
export type MutationDelete_OwnerArgs = {
  id: Scalars["Long"];
};

/** Mutation root */
export type MutationDelete_PetArgs = {
  id: Scalars["Long"];
};

/** Mutation root */
export type MutationDelete_PetTypeArgs = {
  id: Scalars["Long"];
};

/** Mutation root */
export type MutationDelete_TestArgs = {
  id: Scalars["Long"];
};

/** Mutation root */
export type MutationDelete_VisitArgs = {
  id: Scalars["Long"];
};

/** Mutation root */
export type MutationUpdate_OwnerArgs = {
  input?: InputMaybe<OwnerInputDtoInput>;
};

/** Mutation root */
export type MutationUpdate_PetArgs = {
  input?: InputMaybe<PetInputDtoInput>;
};

/** Mutation root */
export type MutationUpdate_PetTypeArgs = {
  input?: InputMaybe<PetTypeInputDtoInput>;
};

/** Mutation root */
export type MutationUpdate_TestArgs = {
  input?: InputMaybe<TestInputDtoInput>;
};

/** Mutation root */
export type MutationUpdate_VisitArgs = {
  input?: InputMaybe<VisitInputDtoInput>;
};

export enum NullHandling {
  Native = "NATIVE",
  NullsFirst = "NULLS_FIRST",
  NullsLast = "NULLS_LAST",
}

export type OrderInput = {
  direction?: InputMaybe<Direction>;
  ignoreCase?: InputMaybe<Scalars["Boolean"]>;
  nullHandlingHint?: InputMaybe<NullHandling>;
  property: Scalars["String"];
};

export type OwnerDto = {
  __typename?: "OwnerDTO";
  address?: Maybe<Scalars["String"]>;
  city?: Maybe<Scalars["String"]>;
  email?: Maybe<Scalars["String"]>;
  firstName?: Maybe<Scalars["String"]>;
  id?: Maybe<Scalars["Long"]>;
  lastName?: Maybe<Scalars["String"]>;
  telephone?: Maybe<Scalars["String"]>;
};

export type OwnerDtoInput = {
  address?: InputMaybe<Scalars["String"]>;
  city?: InputMaybe<Scalars["String"]>;
  email?: InputMaybe<Scalars["String"]>;
  firstName?: InputMaybe<Scalars["String"]>;
  id?: InputMaybe<Scalars["Long"]>;
  lastName?: InputMaybe<Scalars["String"]>;
  telephone?: InputMaybe<Scalars["String"]>;
};

export type OwnerInputDtoInput = {
  address?: InputMaybe<Scalars["String"]>;
  city?: InputMaybe<Scalars["String"]>;
  email?: InputMaybe<Scalars["String"]>;
  firstName?: InputMaybe<Scalars["String"]>;
  id?: InputMaybe<Scalars["Long"]>;
  lastName?: InputMaybe<Scalars["String"]>;
  telephone?: InputMaybe<Scalars["String"]>;
};

export type PaginationInput = {
  pageNumber: Scalars["Int"];
  pageSize?: InputMaybe<Scalars["Int"]>;
  sort?: InputMaybe<SortingInput>;
};

export type PetDto = {
  __typename?: "PetDTO";
  birthDate?: Maybe<Scalars["LocalDate"]>;
  id?: Maybe<Scalars["Long"]>;
  identificationNumber?: Maybe<Scalars["String"]>;
  owner?: Maybe<OwnerDto>;
  type?: Maybe<PetTypeDto>;
};

export type PetDtoInput = {
  birthDate?: InputMaybe<Scalars["LocalDate"]>;
  id?: InputMaybe<Scalars["Long"]>;
  identificationNumber?: InputMaybe<Scalars["String"]>;
  owner?: InputMaybe<OwnerDtoInput>;
  type?: InputMaybe<PetTypeDtoInput>;
};

export type PetInputDtoInput = {
  birthDate?: InputMaybe<Scalars["LocalDate"]>;
  id?: InputMaybe<Scalars["Long"]>;
  identificationNumber?: InputMaybe<Scalars["String"]>;
  owner?: InputMaybe<OwnerDtoInput>;
  type?: InputMaybe<PetTypeDtoInput>;
};

export type PetTypeDto = {
  __typename?: "PetTypeDTO";
  id?: Maybe<Scalars["Long"]>;
  name?: Maybe<Scalars["String"]>;
};

export type PetTypeDtoInput = {
  id?: InputMaybe<Scalars["Long"]>;
  name?: InputMaybe<Scalars["String"]>;
};

export type PetTypeInputDtoInput = {
  id?: InputMaybe<Scalars["Long"]>;
  name?: InputMaybe<Scalars["String"]>;
};

/** Query root */
export type Query = {
  __typename?: "Query";
  owner?: Maybe<OwnerDto>;
  ownerList?: Maybe<Array<Maybe<OwnerDto>>>;
  pet?: Maybe<PetDto>;
  petList?: Maybe<Array<Maybe<PetDto>>>;
  petType?: Maybe<PetTypeDto>;
  petTypeList?: Maybe<Array<Maybe<PetTypeDto>>>;
  test?: Maybe<TestDto>;
  testList?: Maybe<Array<Maybe<TestDto>>>;
  visit?: Maybe<VisitDto>;
  visitList?: Maybe<Array<Maybe<VisitDto>>>;
};

/** Query root */
export type QueryOwnerArgs = {
  id?: InputMaybe<Scalars["Long"]>;
};

/** Query root */
export type QueryPetArgs = {
  id?: InputMaybe<Scalars["Long"]>;
};

/** Query root */
export type QueryPetListArgs = {
  page?: InputMaybe<PaginationInput>;
};

/** Query root */
export type QueryPetTypeArgs = {
  id?: InputMaybe<Scalars["Long"]>;
};

/** Query root */
export type QueryTestArgs = {
  id?: InputMaybe<Scalars["Long"]>;
};

/** Query root */
export type QueryVisitArgs = {
  id?: InputMaybe<Scalars["Long"]>;
};

export type SortingInput = {
  orders: Array<OrderInput>;
};

export type TestDto = {
  __typename?: "TestDTO";
  bigDecimal?: Maybe<Scalars["BigDecimal"]>;
  bigInt?: Maybe<Scalars["BigInteger"]>;
  bool?: Maybe<Scalars["Boolean"]>;
  boolPrimitive: Scalars["Boolean"];
  byteArray?: Maybe<Array<Maybe<Scalars["Byte"]>>>;
  bytePrimitive: Scalars["Byte"];
  byteTest?: Maybe<Scalars["Byte"]>;
  calendar?: Maybe<Calendar>;
  charArray?: Maybe<Array<Maybe<Character>>>;
  charPrimitive: Scalars["Char"];
  character?: Maybe<Scalars["Char"]>;
  currency?: Maybe<Scalars["currency"]>;
  date?: Maybe<Scalars["Date"]>;
  dateUtil?: Maybe<Scalars["DateTime"]>;
  doubleTest?: Maybe<Scalars["Float"]>;
  duration?: Maybe<Duration>;
  floatTest?: Maybe<Scalars["Float"]>;
  id?: Maybe<Scalars["Long"]>;
  instant?: Maybe<Instant>;
  intTest?: Maybe<Scalars["Int"]>;
  localDate?: Maybe<Scalars["LocalDate"]>;
  localDateTime?: Maybe<Scalars["LocalDateTime"]>;
  localTime?: Maybe<Scalars["LocalTime"]>;
  locale?: Maybe<Locale>;
  longTest?: Maybe<Scalars["Long"]>;
  offsetDateTime?: Maybe<Scalars["OffsetDateTime"]>;
  offsetTime?: Maybe<Scalars["OffsetTime"]>;
  shortPrimitive: Scalars["Short"];
  shortTest?: Maybe<Scalars["Short"]>;
  string?: Maybe<Scalars["String"]>;
  time?: Maybe<Scalars["Time"]>;
  timeStamp?: Maybe<Timestamp>;
  url?: Maybe<Url>;
  uuidTest?: Maybe<Scalars["UUID"]>;
  zonedDateTime?: Maybe<ZonedDateTime>;
};

export type TestInputDtoInput = {
  bigDecimal?: InputMaybe<Scalars["BigDecimal"]>;
  bigInt?: InputMaybe<Scalars["BigInteger"]>;
  bool?: InputMaybe<Scalars["Boolean"]>;
  boolPrimitive: Scalars["Boolean"];
  byteArray?: InputMaybe<Array<InputMaybe<ByteInput>>>;
  bytePrimitive: Scalars["Byte"];
  byteTest?: InputMaybe<Scalars["Byte"]>;
  calendar?: InputMaybe<CalendarInput>;
  charArray?: InputMaybe<Array<InputMaybe<CharacterInput>>>;
  charPrimitive: Scalars["Char"];
  character?: InputMaybe<Scalars["Char"]>;
  currency?: InputMaybe<Scalars["currency"]>;
  date?: InputMaybe<DateInput>;
  dateUtil?: InputMaybe<Scalars["DateTime"]>;
  doubleTest?: InputMaybe<Scalars["Float"]>;
  duration?: InputMaybe<DurationInput>;
  floatTest?: InputMaybe<Scalars["Float"]>;
  id?: InputMaybe<Scalars["Long"]>;
  instant?: InputMaybe<InstantInput>;
  intTest?: InputMaybe<Scalars["Int"]>;
  localDate?: InputMaybe<Scalars["LocalDate"]>;
  localDateTime?: InputMaybe<Scalars["LocalDateTime"]>;
  localTime?: InputMaybe<Scalars["LocalTime"]>;
  locale?: InputMaybe<LocaleInput>;
  longTest?: InputMaybe<Scalars["Long"]>;
  offsetDateTime?: InputMaybe<Scalars["OffsetDateTime"]>;
  offsetTime?: InputMaybe<Scalars["OffsetTime"]>;
  shortPrimitive: Scalars["Short"];
  shortTest?: InputMaybe<Scalars["Short"]>;
  string?: InputMaybe<Scalars["String"]>;
  time?: InputMaybe<TimeInput>;
  timeStamp?: InputMaybe<TimestampInput>;
  url?: InputMaybe<UrlInput>;
  uuidTest?: InputMaybe<Scalars["UUID"]>;
  zonedDateTime?: InputMaybe<ZonedDateTimeInput>;
};

export type TimeInput = {
  cdate?: InputMaybe<DateInput>;
};

export type TimeZone = {
  __typename?: "TimeZone";
  ID?: Maybe<Scalars["String"]>;
  zoneId?: Maybe<ZoneId>;
};

export type TimeZoneInput = {
  ID?: InputMaybe<Scalars["String"]>;
  zoneId?: InputMaybe<ZoneIdInput>;
};

export type Timestamp = {
  __typename?: "Timestamp";
  cdate?: Maybe<Scalars["Date"]>;
  nanos: Scalars["Int"];
};

export type TimestampInput = {
  cdate?: InputMaybe<DateInput>;
  nanos: Scalars["Int"];
};

export type Url = {
  __typename?: "URL";
  authority?: Maybe<Scalars["String"]>;
  file?: Maybe<Scalars["String"]>;
  handler?: Maybe<UrlStreamHandler>;
  hashCode: Scalars["Int"];
  host?: Maybe<Scalars["String"]>;
  hostAddress?: Maybe<InetAddress>;
  path?: Maybe<Scalars["String"]>;
  port: Scalars["Int"];
  protocol?: Maybe<Scalars["String"]>;
  query?: Maybe<Scalars["String"]>;
  ref?: Maybe<Scalars["String"]>;
  tempState?: Maybe<UrlDeserializedState>;
  userInfo?: Maybe<Scalars["String"]>;
};

export type UrlInput = {
  authority?: InputMaybe<Scalars["String"]>;
  file?: InputMaybe<Scalars["String"]>;
  handler?: InputMaybe<UrlStreamHandlerInput>;
  hashCode: Scalars["Int"];
  host?: InputMaybe<Scalars["String"]>;
  hostAddress?: InputMaybe<InetAddressInput>;
  path?: InputMaybe<Scalars["String"]>;
  port: Scalars["Int"];
  protocol?: InputMaybe<Scalars["String"]>;
  query?: InputMaybe<Scalars["String"]>;
  ref?: InputMaybe<Scalars["String"]>;
  tempState?: InputMaybe<UrlDeserializedStateInput>;
  userInfo?: InputMaybe<Scalars["String"]>;
};

export type UrlStreamHandler = {
  __typename?: "URLStreamHandler";
};

export type UrlStreamHandlerInput = {};

export type UrlDeserializedState = {
  __typename?: "UrlDeserializedState";
  authority?: Maybe<Scalars["String"]>;
  file?: Maybe<Scalars["String"]>;
  hashCode: Scalars["Int"];
  host?: Maybe<Scalars["String"]>;
  port: Scalars["Int"];
  protocol?: Maybe<Scalars["String"]>;
  ref?: Maybe<Scalars["String"]>;
};

export type UrlDeserializedStateInput = {
  authority?: InputMaybe<Scalars["String"]>;
  file?: InputMaybe<Scalars["String"]>;
  hashCode: Scalars["Int"];
  host?: InputMaybe<Scalars["String"]>;
  port: Scalars["Int"];
  protocol?: InputMaybe<Scalars["String"]>;
  ref?: InputMaybe<Scalars["String"]>;
};

export type VisitDto = {
  __typename?: "VisitDTO";
  description?: Maybe<Scalars["String"]>;
  id?: Maybe<Scalars["Long"]>;
  visitEnd?: Maybe<Scalars["LocalDateTime"]>;
  visitStart?: Maybe<Scalars["LocalDateTime"]>;
};

export type VisitInputDtoInput = {
  description?: InputMaybe<Scalars["String"]>;
  id?: InputMaybe<Scalars["Long"]>;
  pet?: InputMaybe<PetDtoInput>;
  visitEnd?: InputMaybe<Scalars["LocalDateTime"]>;
  visitStart?: InputMaybe<Scalars["LocalDateTime"]>;
};

export type ZoneId = {
  __typename?: "ZoneId";
};

export type ZoneIdInput = {};

export type ZoneOffset = {
  __typename?: "ZoneOffset";
  id?: Maybe<Scalars["String"]>;
  totalSeconds: Scalars["Int"];
};

export type ZoneOffsetInput = {
  id?: InputMaybe<Scalars["String"]>;
  totalSeconds: Scalars["Int"];
};

export type ZonedDateTime = {
  __typename?: "ZonedDateTime";
  dateTime?: Maybe<Scalars["LocalDateTime"]>;
  offset?: Maybe<ZoneOffset>;
  zone?: Maybe<ZoneId>;
};

export type ZonedDateTimeInput = {
  dateTime?: InputMaybe<Scalars["LocalDateTime"]>;
  offset?: InputMaybe<ZoneOffsetInput>;
  zone?: InputMaybe<ZoneIdInput>;
};

export type OwnerQueryVariables = Exact<{
  id?: InputMaybe<Scalars["Long"]>;
}>;

export type OwnerQuery = {
  __typename?: "Query";
  owner?:
    | {
        __typename?: "OwnerDTO";
        address?: string | null | undefined;
        city?: string | null | undefined;
        email?: string | null | undefined;
        firstName?: string | null | undefined;
        id?: any | null | undefined;
        lastName?: string | null | undefined;
        telephone?: string | null | undefined;
      }
    | null
    | undefined;
};

export type Update_OwnerMutationVariables = Exact<{
  input?: InputMaybe<OwnerInputDtoInput>;
}>;

export type Update_OwnerMutation = {
  __typename?: "Mutation";
  update_Owner?:
    | {
        __typename?: "OwnerDTO";
        address?: string | null | undefined;
        city?: string | null | undefined;
        email?: string | null | undefined;
        firstName?: string | null | undefined;
        id?: any | null | undefined;
        lastName?: string | null | undefined;
        telephone?: string | null | undefined;
      }
    | null
    | undefined;
};

export type New_OwnerDtoFragment = {
  __typename?: "OwnerDTO";
  id?: any | null | undefined;
};

export type OwnerListQueryVariables = Exact<{ [key: string]: never }>;

export type OwnerListQuery = {
  __typename?: "Query";
  ownerList?:
    | Array<
        | {
            __typename?: "OwnerDTO";
            address?: string | null | undefined;
            city?: string | null | undefined;
            email?: string | null | undefined;
            firstName?: string | null | undefined;
            id?: any | null | undefined;
            lastName?: string | null | undefined;
            telephone?: string | null | undefined;
          }
        | null
        | undefined
      >
    | null
    | undefined;
};

export type Delete_OwnerMutationVariables = Exact<{
  id: Scalars["Long"];
}>;

export type Delete_OwnerMutation = {
  __typename?: "Mutation";
  delete_Owner: boolean;
};

export type PetQueryVariables = Exact<{
  id?: InputMaybe<Scalars["Long"]>;
}>;

export type PetQuery = {
  __typename?: "Query";
  pet?:
    | {
        __typename?: "PetDTO";
        birthDate?: any | null | undefined;
        id?: any | null | undefined;
        identificationNumber?: string | null | undefined;
        owner?:
          | {
              __typename?: "OwnerDTO";
              address?: string | null | undefined;
              city?: string | null | undefined;
              email?: string | null | undefined;
              firstName?: string | null | undefined;
              id?: any | null | undefined;
              lastName?: string | null | undefined;
              telephone?: string | null | undefined;
            }
          | null
          | undefined;
        type?:
          | {
              __typename?: "PetTypeDTO";
              id?: any | null | undefined;
              name?: string | null | undefined;
            }
          | null
          | undefined;
      }
    | null
    | undefined;
};

export type Update_PetMutationVariables = Exact<{
  input?: InputMaybe<PetInputDtoInput>;
}>;

export type Update_PetMutation = {
  __typename?: "Mutation";
  update_Pet?:
    | {
        __typename?: "PetDTO";
        birthDate?: any | null | undefined;
        id?: any | null | undefined;
        identificationNumber?: string | null | undefined;
      }
    | null
    | undefined;
};

export type New_PetDtoFragment = {
  __typename?: "PetDTO";
  id?: any | null | undefined;
};

export type PetListQueryVariables = Exact<{
  page?: InputMaybe<PaginationInput>;
}>;

export type PetListQuery = {
  __typename?: "Query";
  petList?:
    | Array<
        | {
            __typename?: "PetDTO";
            birthDate?: any | null | undefined;
            id?: any | null | undefined;
            identificationNumber?: string | null | undefined;
            owner?:
              | {
                  __typename?: "OwnerDTO";
                  firstName?: string | null | undefined;
                  lastName?: string | null | undefined;
                }
              | null
              | undefined;
          }
        | null
        | undefined
      >
    | null
    | undefined;
};

export type Delete_PetMutationVariables = Exact<{
  id: Scalars["Long"];
}>;

export type Delete_PetMutation = {
  __typename?: "Mutation";
  delete_Pet: boolean;
};

export const New_OwnerDtoFragmentDoc = {
  kind: "Document",
  definitions: [
    {
      kind: "FragmentDefinition",
      name: { kind: "Name", value: "New_OwnerDTO" },
      typeCondition: {
        kind: "NamedType",
        name: { kind: "Name", value: "OwnerDTO" },
      },
      selectionSet: {
        kind: "SelectionSet",
        selections: [{ kind: "Field", name: { kind: "Name", value: "id" } }],
      },
    },
  ],
} as unknown as DocumentNode<New_OwnerDtoFragment, unknown>;
export const New_PetDtoFragmentDoc = {
  kind: "Document",
  definitions: [
    {
      kind: "FragmentDefinition",
      name: { kind: "Name", value: "New_PetDTO" },
      typeCondition: {
        kind: "NamedType",
        name: { kind: "Name", value: "PetDTO" },
      },
      selectionSet: {
        kind: "SelectionSet",
        selections: [{ kind: "Field", name: { kind: "Name", value: "id" } }],
      },
    },
  ],
} as unknown as DocumentNode<New_PetDtoFragment, unknown>;
export const OwnerDocument = {
  kind: "Document",
  definitions: [
    {
      kind: "OperationDefinition",
      operation: "query",
      name: { kind: "Name", value: "owner" },
      variableDefinitions: [
        {
          kind: "VariableDefinition",
          variable: { kind: "Variable", name: { kind: "Name", value: "id" } },
          type: { kind: "NamedType", name: { kind: "Name", value: "Long" } },
        },
      ],
      selectionSet: {
        kind: "SelectionSet",
        selections: [
          {
            kind: "Field",
            name: { kind: "Name", value: "owner" },
            arguments: [
              {
                kind: "Argument",
                name: { kind: "Name", value: "id" },
                value: {
                  kind: "Variable",
                  name: { kind: "Name", value: "id" },
                },
              },
            ],
            selectionSet: {
              kind: "SelectionSet",
              selections: [
                { kind: "Field", name: { kind: "Name", value: "address" } },
                { kind: "Field", name: { kind: "Name", value: "city" } },
                { kind: "Field", name: { kind: "Name", value: "email" } },
                { kind: "Field", name: { kind: "Name", value: "firstName" } },
                { kind: "Field", name: { kind: "Name", value: "id" } },
                { kind: "Field", name: { kind: "Name", value: "lastName" } },
                { kind: "Field", name: { kind: "Name", value: "telephone" } },
              ],
            },
          },
        ],
      },
    },
  ],
} as unknown as DocumentNode<OwnerQuery, OwnerQueryVariables>;
export const Update_OwnerDocument = {
  kind: "Document",
  definitions: [
    {
      kind: "OperationDefinition",
      operation: "mutation",
      name: { kind: "Name", value: "update_Owner" },
      variableDefinitions: [
        {
          kind: "VariableDefinition",
          variable: {
            kind: "Variable",
            name: { kind: "Name", value: "input" },
          },
          type: {
            kind: "NamedType",
            name: { kind: "Name", value: "OwnerInputDTOInput" },
          },
        },
      ],
      selectionSet: {
        kind: "SelectionSet",
        selections: [
          {
            kind: "Field",
            name: { kind: "Name", value: "update_Owner" },
            arguments: [
              {
                kind: "Argument",
                name: { kind: "Name", value: "input" },
                value: {
                  kind: "Variable",
                  name: { kind: "Name", value: "input" },
                },
              },
            ],
            selectionSet: {
              kind: "SelectionSet",
              selections: [
                { kind: "Field", name: { kind: "Name", value: "address" } },
                { kind: "Field", name: { kind: "Name", value: "city" } },
                { kind: "Field", name: { kind: "Name", value: "email" } },
                { kind: "Field", name: { kind: "Name", value: "firstName" } },
                { kind: "Field", name: { kind: "Name", value: "id" } },
                { kind: "Field", name: { kind: "Name", value: "lastName" } },
                { kind: "Field", name: { kind: "Name", value: "telephone" } },
              ],
            },
          },
        ],
      },
    },
  ],
} as unknown as DocumentNode<
  Update_OwnerMutation,
  Update_OwnerMutationVariables
>;
export const OwnerListDocument = {
  kind: "Document",
  definitions: [
    {
      kind: "OperationDefinition",
      operation: "query",
      name: { kind: "Name", value: "ownerList" },
      selectionSet: {
        kind: "SelectionSet",
        selections: [
          {
            kind: "Field",
            name: { kind: "Name", value: "ownerList" },
            selectionSet: {
              kind: "SelectionSet",
              selections: [
                { kind: "Field", name: { kind: "Name", value: "address" } },
                { kind: "Field", name: { kind: "Name", value: "city" } },
                { kind: "Field", name: { kind: "Name", value: "email" } },
                { kind: "Field", name: { kind: "Name", value: "firstName" } },
                { kind: "Field", name: { kind: "Name", value: "id" } },
                { kind: "Field", name: { kind: "Name", value: "lastName" } },
                { kind: "Field", name: { kind: "Name", value: "telephone" } },
              ],
            },
          },
        ],
      },
    },
  ],
} as unknown as DocumentNode<OwnerListQuery, OwnerListQueryVariables>;
export const Delete_OwnerDocument = {
  kind: "Document",
  definitions: [
    {
      kind: "OperationDefinition",
      operation: "mutation",
      name: { kind: "Name", value: "delete_Owner" },
      variableDefinitions: [
        {
          kind: "VariableDefinition",
          variable: { kind: "Variable", name: { kind: "Name", value: "id" } },
          type: {
            kind: "NonNullType",
            type: { kind: "NamedType", name: { kind: "Name", value: "Long" } },
          },
        },
      ],
      selectionSet: {
        kind: "SelectionSet",
        selections: [
          {
            kind: "Field",
            name: { kind: "Name", value: "delete_Owner" },
            arguments: [
              {
                kind: "Argument",
                name: { kind: "Name", value: "id" },
                value: {
                  kind: "Variable",
                  name: { kind: "Name", value: "id" },
                },
              },
            ],
          },
        ],
      },
    },
  ],
} as unknown as DocumentNode<
  Delete_OwnerMutation,
  Delete_OwnerMutationVariables
>;
export const PetDocument = {
  kind: "Document",
  definitions: [
    {
      kind: "OperationDefinition",
      operation: "query",
      name: { kind: "Name", value: "pet" },
      variableDefinitions: [
        {
          kind: "VariableDefinition",
          variable: { kind: "Variable", name: { kind: "Name", value: "id" } },
          type: { kind: "NamedType", name: { kind: "Name", value: "Long" } },
        },
      ],
      selectionSet: {
        kind: "SelectionSet",
        selections: [
          {
            kind: "Field",
            name: { kind: "Name", value: "pet" },
            arguments: [
              {
                kind: "Argument",
                name: { kind: "Name", value: "id" },
                value: {
                  kind: "Variable",
                  name: { kind: "Name", value: "id" },
                },
              },
            ],
            selectionSet: {
              kind: "SelectionSet",
              selections: [
                { kind: "Field", name: { kind: "Name", value: "birthDate" } },
                { kind: "Field", name: { kind: "Name", value: "id" } },
                {
                  kind: "Field",
                  name: { kind: "Name", value: "identificationNumber" },
                },
                {
                  kind: "Field",
                  name: { kind: "Name", value: "owner" },
                  selectionSet: {
                    kind: "SelectionSet",
                    selections: [
                      {
                        kind: "Field",
                        name: { kind: "Name", value: "address" },
                      },
                      { kind: "Field", name: { kind: "Name", value: "city" } },
                      { kind: "Field", name: { kind: "Name", value: "email" } },
                      {
                        kind: "Field",
                        name: { kind: "Name", value: "firstName" },
                      },
                      { kind: "Field", name: { kind: "Name", value: "id" } },
                      {
                        kind: "Field",
                        name: { kind: "Name", value: "lastName" },
                      },
                      {
                        kind: "Field",
                        name: { kind: "Name", value: "telephone" },
                      },
                    ],
                  },
                },
                {
                  kind: "Field",
                  name: { kind: "Name", value: "type" },
                  selectionSet: {
                    kind: "SelectionSet",
                    selections: [
                      { kind: "Field", name: { kind: "Name", value: "id" } },
                      { kind: "Field", name: { kind: "Name", value: "name" } },
                    ],
                  },
                },
              ],
            },
          },
        ],
      },
    },
  ],
} as unknown as DocumentNode<PetQuery, PetQueryVariables>;
export const Update_PetDocument = {
  kind: "Document",
  definitions: [
    {
      kind: "OperationDefinition",
      operation: "mutation",
      name: { kind: "Name", value: "update_Pet" },
      variableDefinitions: [
        {
          kind: "VariableDefinition",
          variable: {
            kind: "Variable",
            name: { kind: "Name", value: "input" },
          },
          type: {
            kind: "NamedType",
            name: { kind: "Name", value: "PetInputDTOInput" },
          },
        },
      ],
      selectionSet: {
        kind: "SelectionSet",
        selections: [
          {
            kind: "Field",
            name: { kind: "Name", value: "update_Pet" },
            arguments: [
              {
                kind: "Argument",
                name: { kind: "Name", value: "input" },
                value: {
                  kind: "Variable",
                  name: { kind: "Name", value: "input" },
                },
              },
            ],
            selectionSet: {
              kind: "SelectionSet",
              selections: [
                { kind: "Field", name: { kind: "Name", value: "birthDate" } },
                { kind: "Field", name: { kind: "Name", value: "id" } },
                {
                  kind: "Field",
                  name: { kind: "Name", value: "identificationNumber" },
                },
              ],
            },
          },
        ],
      },
    },
  ],
} as unknown as DocumentNode<Update_PetMutation, Update_PetMutationVariables>;
export const PetListDocument = {
  kind: "Document",
  definitions: [
    {
      kind: "OperationDefinition",
      operation: "query",
      name: { kind: "Name", value: "petList" },
      variableDefinitions: [
        {
          kind: "VariableDefinition",
          variable: { kind: "Variable", name: { kind: "Name", value: "page" } },
          type: {
            kind: "NamedType",
            name: { kind: "Name", value: "PaginationInput" },
          },
        },
      ],
      selectionSet: {
        kind: "SelectionSet",
        selections: [
          {
            kind: "Field",
            name: { kind: "Name", value: "petList" },
            arguments: [
              {
                kind: "Argument",
                name: { kind: "Name", value: "page" },
                value: {
                  kind: "Variable",
                  name: { kind: "Name", value: "page" },
                },
              },
            ],
            selectionSet: {
              kind: "SelectionSet",
              selections: [
                { kind: "Field", name: { kind: "Name", value: "birthDate" } },
                { kind: "Field", name: { kind: "Name", value: "id" } },
                {
                  kind: "Field",
                  name: { kind: "Name", value: "identificationNumber" },
                },
                {
                  kind: "Field",
                  name: { kind: "Name", value: "owner" },
                  selectionSet: {
                    kind: "SelectionSet",
                    selections: [
                      {
                        kind: "Field",
                        name: { kind: "Name", value: "firstName" },
                      },
                      {
                        kind: "Field",
                        name: { kind: "Name", value: "lastName" },
                      },
                    ],
                  },
                },
              ],
            },
          },
        ],
      },
    },
  ],
} as unknown as DocumentNode<PetListQuery, PetListQueryVariables>;
export const Delete_PetDocument = {
  kind: "Document",
  definitions: [
    {
      kind: "OperationDefinition",
      operation: "mutation",
      name: { kind: "Name", value: "delete_Pet" },
      variableDefinitions: [
        {
          kind: "VariableDefinition",
          variable: { kind: "Variable", name: { kind: "Name", value: "id" } },
          type: {
            kind: "NonNullType",
            type: { kind: "NamedType", name: { kind: "Name", value: "Long" } },
          },
        },
      ],
      selectionSet: {
        kind: "SelectionSet",
        selections: [
          {
            kind: "Field",
            name: { kind: "Name", value: "delete_Pet" },
            arguments: [
              {
                kind: "Argument",
                name: { kind: "Name", value: "id" },
                value: {
                  kind: "Variable",
                  name: { kind: "Name", value: "id" },
                },
              },
            ],
          },
        ],
      },
    },
  ],
} as unknown as DocumentNode<Delete_PetMutation, Delete_PetMutationVariables>;
